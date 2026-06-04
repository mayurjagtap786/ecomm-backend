package org.shopping.kart.inventory.service;

import jakarta.transaction.Transactional;
import org.shopping.kart.inventory.dto.InventoryRequest;
import org.shopping.kart.inventory.exception.InsufficientStockException;
import org.shopping.kart.inventory.exception.InventoryNotFound;
import org.shopping.kart.inventory.model.Inventory;
import org.shopping.kart.inventory.repository.InventoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    InventoryService(InventoryRepository inventoryRepository){
        this.repository = inventoryRepository;

    }

    public Inventory createInventory(InventoryRequest inventoryRequest){

        Inventory inventory = new Inventory(
                inventoryRequest.productId(),
                inventoryRequest.quantity(),
                0);

        return repository.save(inventory);
    }
    public Inventory getInventory(Long productId){
        return repository.findByProductId(productId).orElseThrow(() -> new InventoryNotFound(productId));
    }

    public boolean isStockAvailable(Long productId, Integer quantity){
        Inventory inventory = getInventory(productId);

        return inventory.getAvailableQty() >= quantity;
    }

        @Transactional
        public void reservedStock(InventoryRequest request){
            Inventory inventory = getInventory(request.productId());
            int availableQty = inventory.getAvailableQty();;
            int requestQty = request.quantity();
            if(availableQty < requestQty){
                throw new InsufficientStockException(request.productId());
            }
            inventory.setAvailableQty(availableQty - requestQty);
            inventory.setReservedQty(inventory.getReservedQty() + requestQty);

            //repository.save(inventory);
        }


    public ResponseEntity<?> releaseStock(InventoryRequest request){
        Inventory inventory = getInventory(request.productId());
        if (inventory.getReservedQty()!=null && inventory.getReservedQty() > 0) {
        inventory.setAvailableQty(inventory.getAvailableQty() + request.quantity());
        inventory.setReservedQty(inventory.getReservedQty() - request.quantity());
        repository.save(inventory);
        return ResponseEntity.ok("Inventory has been cancelled...");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<?> confirmStock(InventoryRequest request){
        Inventory inventory = getInventory(request.productId());
            if(inventory.getReservedQty() < request.quantity()){
                throw new RuntimeException("Insufficient reserved stock");
            }
            inventory.setReservedQty(inventory.getReservedQty() - request.quantity());
            repository.save(inventory);
            return ResponseEntity.ok("Inventory Confirmed...");
    }
}
