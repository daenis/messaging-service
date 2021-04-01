package com.daenis.messaging.core.email;

import com.daenis.messaging.core.shared.Message;
import com.daenis.messaging.core.shared.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class EmailService implements MessageService {

    private final EmailSender sender;
    private final EmailMapper mapper;

    @Override
    public void sendMessage(Message theMessage) {
        try {
            send(theEmailMappedFrom(theMessage));
        } catch (MessagingException e) {
            throw anExceptionFor(theMessage);
        }
    }

    private Email theEmailMappedFrom(Message theMessage) throws MessagingException {
        return mapper.getEmailForMessage(theMessage);
    }

    private void send(Email theEmail) {
        sender.send(theEmail);
    }

    RuntimeException anExceptionFor(Message theMessage) {
        return new RuntimeException("There was an error while sending a message to " + theMessage.getRecipient() + " with subject: " + theMessage.getSubject());
    }
}
