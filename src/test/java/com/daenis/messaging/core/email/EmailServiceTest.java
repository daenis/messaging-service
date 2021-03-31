package com.daenis.messaging.core.email;

import com.daenis.messaging.core.shared.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    private String recipient;
    private String subject;
    private Message message;

    private Email email;

    @Mock
    private EmailSender sender;

    @Mock
    private EmailMapper mapper;

    private EmailService emailService;

    @BeforeEach
    void init() throws MessagingException {
        recipient = "someone@something.net";
        subject = "something important";
        message = new Message() {
            @Override public String getRecipient() { return recipient; }
            @Override public String getSubject() { return subject; }
            @Override public String getBody() { return null; }
        };

        email = Email.getInstanceWithMimeMessage(new MimeMessage(Session.getInstance(new Properties())));

        emailService = new EmailService(sender, mapper);
    }

    @Test
    void shouldSendTheEmailMappedFromTheMessage() throws MessagingException {
        when(mapper.getEmailForMessage(message)).thenReturn(email);

        emailService.sendMessage(message);

        verify(sender, times(1)).send(email);
    }

    @Test
    void shouldThrowAnExceptionForTheMessageIfMessagingExceptionOccurs() throws MessagingException {
        String predictedMessage = "There was an error while sending a message to " + recipient + " with subject: " + subject;

        when(mapper.getEmailForMessage(message)).thenThrow(new MessagingException());

        MessagingErrorException thrown = assertThrows(MessagingErrorException.class, () -> emailService.sendMessage(message));

        assertThat(thrown.getMessage()).isEqualTo(predictedMessage);
    }
}