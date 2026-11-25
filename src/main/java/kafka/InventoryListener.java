package com.example.ecommerce.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryListener {
    @KafkaListener(topics = "order-events")
    public void listen(ConsumerRecord<Long, String> record) {
        System.out.println("=====================================");
        System.out.println("📥 库存服务收到消息 (准备扣减库存)");
        System.out.println("订单 ID: " + record.key());
        System.out.println("消息内容: " + record.value());
        System.out.println("=====================================");
    }
}