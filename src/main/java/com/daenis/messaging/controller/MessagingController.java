package com.daenis.messaging.controller;

import com.daenis.messaging.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessagingController {

    private final MessagingService service;

    @PostMapping("/api/messaging/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        service.sendMessage(message);
        return ResponseEntity.ok().build();
    }
}
