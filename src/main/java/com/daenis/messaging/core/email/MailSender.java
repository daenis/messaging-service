package com.daenis.messaging.core.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

// TODO: test
@Service
@RequiredArgsConstructor
public class MailSender implements MessageFactory, EmailSender {

    private final JavaMailSender javaMailSender;

    @Override
    public MimeMessage createMimeMessage() {
        return javaMailSender.createMimeMessage();
    }

    @Override
    public void send(Email email) {
        javaMailSender.send(email.get());
    }
}
