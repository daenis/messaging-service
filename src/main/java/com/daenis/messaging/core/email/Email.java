package com.daenis.messaging.core.email;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class Email {

    private MimeMessage mimeMessage;
    private MimeMessageHelper mimeMessageHelper;

    static Email getInstanceWithMimeMessage(MimeMessage mimeMessage) throws MessagingException {
        return new Email(mimeMessage);
    }

    private Email(MimeMessage mimeMessage) throws MessagingException {
        this.mimeMessage = mimeMessage;
        this.mimeMessageHelper = new MimeMessageHelper(this.mimeMessage, true);
    }

    Email withRecipient(String recipient) throws MessagingException {
        mimeMessageHelper.setTo(recipient);
        return this;
    }

    Email withSubject(String subject) throws MessagingException {
        mimeMessageHelper.setSubject(subject);
        return this;
    }

    Email withBody(String body) throws MessagingException {
        mimeMessageHelper.setText(body, true);
        return this;
    }

    MimeMessage get() {
        return mimeMessage;
    }
}
