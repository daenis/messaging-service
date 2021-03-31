package com.daenis.messaging.controller;

import com.daenis.messaging.core.shared.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientMessage implements Message {

    private String recipient;
    private String subject;
    private String body;
}
