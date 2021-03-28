package com.daenis.messaging.email;

import com.daenis.messaging.controller.Message;
import com.daenis.messaging.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements MessagingService {

    private final EmailSender emailSender;

    @Override
    public void sendMessage(Message message) {
        emailSender.sendEmail(new Email()
                .initialize()
                .withSystemEmail()
                .withRecipient(message.getRecipient())
                .withSubject(message.getSubject())
                .withText(message.getMessage()));
    }
}
