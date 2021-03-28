package com.daenis.messaging.controller;

import com.daenis.messaging.service.MessagingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MessagingControllerTest {

    private Message message;

    @Mock
    private MessagingService service;

    private MessagingController messagingController;

    @BeforeEach
    void init() {
        message = new Message();

        messagingController = new MessagingController(service);
    }

    @Test
    void shouldSendMessage() {
        messagingController.sendMessage(message);

        verify(service, times(1)).sendMessage(message);
    }

    @Test
    void shouldReturnSuccessfulResponseAfterSendingMessage() {
        ResponseEntity<?> predictedResponse = ResponseEntity.ok().build();

        ResponseEntity<?> returnedResponse = messagingController.sendMessage(message);

        assertThat(returnedResponse).isEqualTo(predictedResponse);
    }
}
