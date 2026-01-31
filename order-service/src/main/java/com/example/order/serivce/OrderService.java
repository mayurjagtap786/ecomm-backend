package com.example.order.serivce;

import com.example.order.dto.OrderRecord;
import com.example.order.entity.Order;
import com.example.order.enums.OrderStatus;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    OrderRepository orderRepository;
    private static final String INVENTORY_SERVICE="http://localhost:8085/inventory";

    @Autowired
    private RestTemplate restTemplate;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<?> createOrder(OrderRecord orderdRecord){
        Order order = new Order(
                UUID.randomUUID().toString(),
                orderdRecord.productId(),
                orderdRecord.quantity(),
                OrderStatus.CREATED,
                orderdRecord.amount(),
                LocalDateTime.now()
        );
        orderRepository.save(order);
        //call the inventory-service
        Map<String, Object> inventoryRequest = new HashMap<>();
        inventoryRequest.put("productId", order.getProductId());
        inventoryRequest.put("quantity",order.getQuantity());
        String response =  restTemplate.postForObject(INVENTORY_SERVICE.concat("/add"),inventoryRequest, String.class);
        System.out.println("Inventory Service Response :"+response);
        return ResponseEntity.ok(response);
    }
    public void confirmOrder(String orderId){
        Order order = orderRepository.findByOrderId(orderId);


        //CALL THE inventory-service
        Map<String, Object> inventoryRequest = new HashMap<>();
        inventoryRequest.put("productId", order.getProductId());
        inventoryRequest.put("quantity",order.getQuantity());
        restTemplate.postForObject(INVENTORY_SERVICE.concat("/reserve"),inventoryRequest, Void.class);

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);


    }

    public void cancelOrder(String productId){
        Order order = orderRepository.findByOrderId(productId);

        //CALL THE inventory-service to cancelled the order
        Map<String, Object> inventoryRequest = new HashMap<>();
        inventoryRequest.put("productId", order.getProductId());
        inventoryRequest.put("quantity",order.getQuantity());
        restTemplate.postForObject(INVENTORY_SERVICE.concat("/relese"),inventoryRequest, Void.class);

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

    }
}
