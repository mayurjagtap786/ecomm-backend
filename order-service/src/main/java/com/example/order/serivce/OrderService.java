package com.example.order.serivce;

import com.example.order.dto.OrderRecord;
import com.example.order.entity.Order;
import com.example.order.enums.OrderStatus;
import com.example.order.proxy.InventoryServiceProxy;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    OrderRepository orderRepository;
    private static final String INVENTORY_SERVICE="http://localhost:8085/inventory";
    InventoryServiceProxy inventoryServiceProxy;

    @Autowired
    private RestTemplate restTemplate;
    public OrderService(OrderRepository orderRepository, InventoryServiceProxy inventoryServiceProxy) {
        this.orderRepository = orderRepository;
        this.inventoryServiceProxy = inventoryServiceProxy;
    }
    
    
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    /* If Order is created then order quantity will be deducted from inventory available_quantity
            and deducted inventory will be stored to reserved_quantity
    */
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
        //call the inventory-service reserved
        Map<String, Object> inventoryRequest = new HashMap<>();
        inventoryRequest.put("productId", order.getProductId());
        inventoryRequest.put("quantity",order.getQuantity());
        //String response =  restTemplate.postForObject(INVENTORY_SERVICE.concat("/add"),inventoryRequest, String.class);
         ResponseEntity<String>response = inventoryServiceProxy.reserveInventory(inventoryRequest);
        System.out.println("Inventory Service Response while creating order :"+response.getBody());
        return ResponseEntity.ok(response);
    }
    /* If order is confirmed then Inventory will be deducted from available_quantity
        & reserved_quantity will set to zero */
    public void confirmOrder(String orderId){
        Order order = orderRepository.findByOrderId(orderId);
        //CALL THE inventory-service deduct
        Map<String, Object> inventoryRequest = new HashMap<>();
        inventoryRequest.put("productId", order.getProductId());
        inventoryRequest.put("quantity",order.getQuantity());
        //restTemplate.postForObject(INVENTORY_SERVICE.concat("/reserve"),inventoryRequest, Void.class);
        ResponseEntity<String> response = inventoryServiceProxy.confirmInventory(inventoryRequest);
        System.out.println("Inventory Service Response :"+response.getBody());
        if(response.getStatusCode().is2xxSuccessful()){
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.save(order);
        }
    }

    /* If Order is cancelled then inventory will be released */
    public void cancelOrder(String productId){
        Order order = orderRepository.findByOrderId(productId);
        //CALL THE inventory-service to cancelled the order
        Map<String, Object> inventoryRequest = new HashMap<>();
        inventoryRequest.put("productId", order.getProductId());
        inventoryRequest.put("quantity",order.getQuantity());
        //restTemplate.postForObject(INVENTORY_SERVICE.concat("/relese"),inventoryRequest, Void.class);
        ResponseEntity<String> response = inventoryServiceProxy.cancelInventory(inventoryRequest);
        System.out.println("cancel order response :"+response);
        if(response.getStatusCode().is2xxSuccessful()){
            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
        }

    }
}
