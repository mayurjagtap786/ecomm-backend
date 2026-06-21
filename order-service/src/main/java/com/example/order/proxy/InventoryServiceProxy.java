package com.example.order.proxy;

import com.example.order.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "inventoryservice" , configuration = FeignClientConfiguration.class)
public interface InventoryServiceProxy {

    @PostMapping("/inventory/reserve")
    public ResponseEntity<String> reserveInventory(@RequestHeader("ecomm-correlation-id") String correlationId,Map<String, Object> inventoryMap);

    @PostMapping("/inventory/confirm")
    public ResponseEntity<String> confirmInventory(Map<String,Object> inventoryMap);

    @PostMapping("/inventory/cancel")
    public ResponseEntity<String> cancelInventory(Map<String,Object> inventorymap);


}
