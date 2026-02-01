package com.example.order.controller;

import com.example.order.dto.OrderRecord;
import com.example.order.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {


    public OrderService orderService;

    @Autowired
    RestTemplate restTemplate;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?>createOrder(@RequestBody OrderRecord orderRecord){
        orderService.createOrder(orderRecord);

        return ResponseEntity.ok("Order Created");
    }
    @GetMapping("/confirm/{orderId}")
    public ResponseEntity<?> confirmOrder(@PathVariable String orderId){
        orderService.confirmOrder(orderId);
        return ResponseEntity.ok("Order Confirmed");
    }

    @GetMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable String orderId){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order cancelled");
    }

}
