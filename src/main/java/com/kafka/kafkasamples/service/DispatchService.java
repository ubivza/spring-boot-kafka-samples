package com.kafka.kafkasamples.service;

import com.kafka.kafkasamples.message.OrderCreated;
import com.kafka.kafkasamples.message.OrderDispatched;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchService {

    //Выносить в отдельный класс
    private static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";
    private final KafkaTemplate<String, Object> kafkaProducer;

    public void process(OrderCreated payload) throws ExecutionException, InterruptedException {
        OrderDispatched orderDispatched = OrderDispatched.builder()
            .orderId(payload.getOrderId())
            .build();
        //асинхронно - послал и забыл, если сообщение не дошло до кафки, мы об этом не узнаем
        //kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched);

        //Синхронно ждем что месседж дошел до кафки
        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched).get();
    }
}
