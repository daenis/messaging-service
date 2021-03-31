package com.daenis.messaging.core.email;

import com.daenis.messaging.core.shared.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

// TODO: test
@Component
@RequiredArgsConstructor
public class EmailMapper {

    private final HtmlMapper htmlMapper;
    private final MessageFactory messageFactory;

    Email getEmailForMessage(Message message) throws MessagingException {
        MimeMessage mimeMessage = messageFactory.createMimeMessage();

        return Email
                .getInstanceWithMimeMessage(mimeMessage)
                .withRecipient(message.getRecipient())
                .withSubject(message.getSubject())
                .withBody(htmlMapper.getHtmlBodyFromMessage(message));
    }
}
