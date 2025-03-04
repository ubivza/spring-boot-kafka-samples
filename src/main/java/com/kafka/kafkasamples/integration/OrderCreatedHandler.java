package com.kafka.kafkasamples.integration;

import com.kafka.kafkasamples.message.OrderCreated;
import com.kafka.kafkasamples.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedHandler {

    private final DispatchService service;

    @KafkaListener(
        id = "orderConsumerClient",
        topics = "order.created",
        groupId = "dispatch.order.consumer", //айди косьюмер группы
        containerFactory = "kafkaListenerContainerFactory" //тот что настроили в конфиге
    )
    public void listen(OrderCreated payload) {
        log.info("Received message {}", payload);
        service.process(payload);
    }
}
