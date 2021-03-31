package com.daenis.messaging.core.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailTest {

    private String recipient;
    private String subject;
    private String body;

    private Email email;

    @BeforeEach
    void init() throws MessagingException {
        recipient = "someone@someDomain.com";
        subject = "The Numbers Mason";
        body = "<h1>What do they mean?!?!</h1>";

        email = Email.getInstanceWithMimeMessage(new MimeMessage(Session.getInstance(new Properties())));
    }

    @Test
    void shouldSetTheRecipient() throws MessagingException {
        email.withRecipient(recipient);

        assertThat(email.get().getAllRecipients()[0].toString()).isEqualTo(recipient);
    }

    @Test
    void shouldSetTheSubject() throws MessagingException {
        email.withSubject(subject);

        assertThat(email.get().getSubject()).isEqualTo(subject);
    }

    @Test
    void shouldSetTheBody() throws MessagingException, IOException {
        email.withBody(body);

        assertThat(getAttachedHtmlAsString()).isEqualTo(body);
    }

    private String getAttachedHtmlAsString() throws MessagingException, IOException {
        MimeMultipart mimeMultipart = (MimeMultipart) email.get().getContent();
        BodyPart bodyPart = mimeMultipart.getBodyPart(0);
        MimeMultipart html = (MimeMultipart) bodyPart.getContent();
        return (String) html.getBodyPart(0).getContent();
    }
}
