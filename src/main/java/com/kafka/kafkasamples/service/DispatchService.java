package com.kafka.kafkasamples.service;

import com.kafka.kafkasamples.message.OrderCreated;
import org.springframework.stereotype.Service;

@Service
public class DispatchService {

    public void process(OrderCreated payload) {

    }
}
