package com.daenis.messaging.core.shared;

public interface Message {

    String getRecipient();

    String getSubject();

    String getBody();
}
