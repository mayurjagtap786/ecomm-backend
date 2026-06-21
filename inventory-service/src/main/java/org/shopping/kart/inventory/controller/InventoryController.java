package org.shopping.kart.inventory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.shopping.kart.inventory.dto.InventoryContactInfoDTO;
import org.shopping.kart.inventory.dto.InventoryRequest;
import org.shopping.kart.inventory.model.Inventory;
import org.shopping.kart.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(
        name="REST API of Inventory Service in Ecommerce Application",
        description="It includes ADD,RESERVE,CANCEL,CONFIRM,GET-INVENTORY APIs"
)
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private Environment environment;

    @Autowired
    private InventoryContactInfoDTO inventoryContactInfoDTO;

    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Create Inventory REST-API",
            description = "REST API to create new inventory in Ecommerce Application"
    )
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
    public ResponseEntity<Object> reserveInventory(@RequestHeader("ecomm-correlation-id") String correlationId,@RequestBody InventoryRequest inventoryRequest){
        LOG.debug("ecomm-services correlation-id found {}",correlationId);
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

    @GetMapping("/build-info")
    public ResponseEntity<String> buildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<?> contactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryContactInfoDTO);
    }
}