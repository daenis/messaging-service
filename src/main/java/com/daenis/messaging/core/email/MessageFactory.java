package com.daenis.messaging.core.email;

import javax.mail.internet.MimeMessage;

public interface MessageFactory {

    MimeMessage createMimeMessage();
}
