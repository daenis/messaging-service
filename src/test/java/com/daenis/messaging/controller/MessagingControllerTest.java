package com.daenis.messaging.controller;

import com.daenis.messaging.core.shared.MessageService;
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

    private ClientMessage clientMessage;

    @Mock
    private MessageService service;

    private MessagingController messagingController;

    @BeforeEach
    void init() {
        clientMessage = new ClientMessage();

        messagingController = new MessagingController(service);
    }

    @Test
    void shouldSendMessage() {
        messagingController.sendMessage(clientMessage);

        verify(service, times(1)).sendMessage(clientMessage);
    }

    @Test
    void shouldReturnSuccessfulResponseAfterSendingMessage() {
        ResponseEntity<?> predictedResponse = ResponseEntity.ok().build();

        ResponseEntity<?> returnedResponse = messagingController.sendMessage(clientMessage);

        assertThat(returnedResponse).isEqualTo(predictedResponse);
    }
}
