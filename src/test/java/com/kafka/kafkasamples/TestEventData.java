package com.kafka.kafkasamples;

import com.kafka.kafkasamples.message.OrderCreated;
import java.util.UUID;

public class TestEventData {

    public static OrderCreated buildCratedEvent(UUID orderId, String item) {
        return OrderCreated.builder()
            .orderId(orderId)
            .item(item)
            .build();
    }
}
