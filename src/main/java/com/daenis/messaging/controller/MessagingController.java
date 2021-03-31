package com.daenis.messaging.controller;

import com.daenis.messaging.core.shared.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessagingController {

    private final MessageService service;

    @PostMapping("/api/messaging/send")
    public ResponseEntity<?> sendMessage(@RequestBody ClientMessage clientMessage) {
        service.sendMessage(clientMessage);
        return ResponseEntity.ok().build();
    }
}
