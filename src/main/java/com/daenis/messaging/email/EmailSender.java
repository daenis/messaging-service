package com.daenis.messaging.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender mailSender;

    void sendEmail(Email email) {
        mailSender.send(email.getSimpleMailMessage());
    }
}
