package com.example.order.controller;

import com.example.order.constants.OrderConstants;
import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderRecord;
import com.example.order.dto.OrderServiceContactInfo;
import com.example.order.dto.ResponseDTO;
import com.example.order.entity.Order;
import com.example.order.serivce.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    public OrderService orderService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private OrderServiceContactInfo orderServiceContactInfo;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/fetch")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/create")
    public ResponseEntity<?>createOrder(@RequestHeader("ecomm-correlation-id") String correlationId,@RequestBody OrderRecord orderRecord){
        LOG.debug("ecomm-services correlation-id found {}",correlationId);
       ResponseEntity<?> response =  orderService.createOrder(orderRecord,correlationId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ResponseDTO(String.valueOf(HttpStatus.CREATED),
                                OrderConstants.MESSAGE_201)
                );
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


    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);

    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));

    }

    @GetMapping("/contact-info")
    public ResponseEntity<OrderServiceContactInfo> getContactInfo(){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(orderServiceContactInfo);

    }
}