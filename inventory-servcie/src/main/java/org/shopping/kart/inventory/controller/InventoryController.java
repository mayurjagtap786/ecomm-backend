package org.shopping.kart.inventory.controller;

import org.shopping.kart.inventory.dto.InventoryRequest;
import org.shopping.kart.inventory.model.Inventory;
import org.shopping.kart.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private Environment environment;

    @PostMapping("/add")
    public ResponseEntity<String> addInventory(@RequestBody InventoryRequest inventoryRequest){
        Inventory inventory = inventoryService.createInventory(inventoryRequest);
        return ResponseEntity.ok("Stock added successfully");
    }

    @GetMapping("/{productId}")
    public Inventory getInventory(@PathVariable Long productId){
        return inventoryService.getInventory(productId);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Object> reserveInventory(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.reservedStock(inventoryRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Stock Reserved from environment "+environment.getProperty("server.port"));
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> release(@RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.releaseStock(inventoryRequest);

    }

    @PostMapping("/confirm")
    public ResponseEntity<?> deduct(@RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.confirmStock(inventoryRequest);
    }
}
