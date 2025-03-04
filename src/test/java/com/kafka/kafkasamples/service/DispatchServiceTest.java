package com.kafka.kafkasamples.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.kafka.kafkasamples.TestEventData;
import com.kafka.kafkasamples.message.OrderCreated;
import com.kafka.kafkasamples.message.OrderDispatched;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.kafka.core.KafkaTemplate;

class DispatchServiceTest {

    private DispatchService service;
    @Mock
    private KafkaTemplate kafkaProducerMock;

    @Test
    void process() throws Exception {
        when(kafkaProducerMock.send(anyString(), any(OrderDispatched.class))).thenReturn(mock(CompletableFuture.class));

        OrderCreated testEvent = TestEventData.buildCratedEvent(UUID.randomUUID(),
            UUID.randomUUID().toString());
        service.process(testEvent);

        verify(kafkaProducerMock, times(1)).send(eq("order.dispatched"),
            any(OrderDispatched.class));
    }
}