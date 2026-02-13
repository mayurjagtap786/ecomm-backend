package com.example.order.controller;

import com.example.order.dto.OrderRecord;
import com.example.order.entity.Order;
import com.example.order.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


    public OrderService orderService;

    @Autowired
    RestTemplate restTemplate;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/fetch")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    @PostMapping
    public ResponseEntity<?>createOrder(@RequestBody OrderRecord orderRecord){
       ResponseEntity<?> response =  orderService.createOrder(orderRecord);

        return ResponseEntity.ok("Order Created "+response.getBody());
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