package com.example.ecommerce.controller;

import com.example.ecommerce.kafka.OrderProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders") // 必须有这个
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/{orderId}") // 必须有这个
    public String createOrder(@PathVariable Long orderId, @RequestBody String productInfo) {
        orderProducer.sendOrderEvent(orderId, "用户下单了商品: " + productInfo);
        return "订单 " + orderId + " 创建成功，已通知库存系统！";
    }
}