package com.daenis.messaging.core.email;

import com.daenis.messaging.core.shared.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailMapperTest {

    private final ObjectMapper MAPPER = new ObjectMapper();

    private String recipient;
    private String subject;
    private Message message;

    private MimeMessage mimeMessage;

    private String htmlBody;

    @Mock
    private HtmlMapper htmlMapper;

    @Mock
    private MessageFactory messageFactory;

    private EmailMapper emailMapper;

    @BeforeEach
    void init() {
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        recipient = "someone@something.com";
        subject = "Something Important";
        message = new Message() {
            @Override public String getRecipient() { return recipient; }
            @Override public String getSubject() { return subject; }
            @Override public String getBody() { return null; }
        };

        mimeMessage = new MimeMessage(Session.getInstance(new Properties()));

        htmlBody = "<h1>hi!</h1>";

        emailMapper = new EmailMapper(htmlMapper, messageFactory);
    }

    @Test
    void shouldGetTheEmailForTheMessage() throws JsonProcessingException, MessagingException {
        String predictedEmailAsString = MAPPER.writeValueAsString(getPredictedEmail());

        when(messageFactory.createMimeMessage()).thenReturn(mimeMessage);
        when(htmlMapper.getHtmlBodyFromMessage(message)).thenReturn(htmlBody);

        Email returnedEmail = emailMapper.getEmailForMessage(message);

        String returnedEmailAsString = MAPPER.writeValueAsString(returnedEmail);
        assertThat(returnedEmailAsString).isEqualTo(predictedEmailAsString);
    }

    private Email getPredictedEmail() throws MessagingException {
        return Email
                .getInstanceWithMimeMessage(mimeMessage)
                .withRecipient(recipient)
                .withSubject(subject)
                .withBody(htmlBody);
    }
}