package com.example.ecommerce.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private final KafkaTemplate<Long, String> kafkaTemplate;

    public OrderProducer(KafkaTemplate<Long, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(Long orderId, String message) {
        System.out.println("📤 正在发送订单消息到 Kafka: 订单ID=" + orderId);
        kafkaTemplate.send("order-events", orderId, message);
    }
}