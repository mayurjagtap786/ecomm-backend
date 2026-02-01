package com.example.order.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "inventory-service", url="localhost:8085")
public interface InventoryServiceProxy {

    @PostMapping("/inventory/reserve")
    public ResponseEntity<String> reserveInventory(Map<String,Object> inventoryMap);

    @PostMapping("/inventory/deduct")
    public ResponseEntity<String> deductInventory(Map<String,Object> inventoryMap);


}
