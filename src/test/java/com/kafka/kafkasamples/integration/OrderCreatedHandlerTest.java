package com.kafka.kafkasamples.integration;

import static org.mockito.Mockito.*;

import com.kafka.kafkasamples.TestEventData;
import com.kafka.kafkasamples.service.DispatchService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class OrderCreatedHandlerTest {

    @InjectMocks
    private OrderCreatedHandler handler;
    @Mock
    private DispatchService serviceMock;

    @Test
    void listen() {
        var payload = TestEventData.buildCratedEvent(UUID.randomUUID(), "scissors");
        handler.listen(payload);

        verify(serviceMock, times(1)).process(payload);
    }
}