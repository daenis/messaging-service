package com.daenis.messaging.core.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppMailSenderTest {

    private MimeMessage mimeMessage;
    private Email email;

    @Mock
    private JavaMailSender javaMailSender;

    private AppMailSender appMailSender;

    @BeforeEach
    void init() throws MessagingException {
        mimeMessage = new MimeMessage(Session.getInstance(new Properties()));
        email = Email.getInstanceWithMimeMessage(mimeMessage);

        appMailSender = new AppMailSender(javaMailSender);
    }

    @Test
    void shouldCreateMimeMessage() {
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        MimeMessage returnedMimeMessage = appMailSender.createMimeMessage();

        assertThat(returnedMimeMessage).isEqualTo(mimeMessage);
    }

    @Test
    void shouldSendTheEmail() {
        appMailSender.send(email);

        verify(javaMailSender, times(1)).send(mimeMessage);
    }
}