package com.daenis.messaging.email;

import org.springframework.mail.SimpleMailMessage;

class Email {

    private SimpleMailMessage simpleMailMessage;

    Email initialize() {
        simpleMailMessage = new SimpleMailMessage();
        return this;
    }

    Email withSystemEmail() {
        simpleMailMessage.setFrom("dkalaygia@gmail.com");
        return this;
    }

    Email withRecipient(String recipient) {
        simpleMailMessage.setTo(recipient);
        return this;
    }

    Email withSubject(String subject) {
        simpleMailMessage.setSubject(subject);
        return this;
    }

    Email withText(String text) {
        simpleMailMessage.setText(text);
        return this;
    }

    SimpleMailMessage getSimpleMailMessage() {
        return simpleMailMessage;
    }
}
