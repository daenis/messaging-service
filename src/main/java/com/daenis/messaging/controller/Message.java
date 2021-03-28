package com.daenis.messaging.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String recipient;
    private String subject;
    private String message;
}
