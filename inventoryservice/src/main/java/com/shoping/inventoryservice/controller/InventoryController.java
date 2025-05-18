package com.shoping.inventoryservice.controller;

import com.shoping.inventoryservice.entity.Inventory;
import com.shoping.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")

public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.saveInventory(inventory));
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAll() {
        return ResponseEntity.ok(inventoryService.getAll());
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<Inventory> getByCode(@PathVariable String productCode) {
        return inventoryService.getByProductCode(productCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{productCode}/add/{qty}")
    public ResponseEntity<String> addQuantity(@PathVariable String productCode, @PathVariable int qty) {
        inventoryService.updateQuantity(productCode, qty);
        return ResponseEntity.ok("Quantity updated successfully");
    }

    @PutMapping("/{productCode}/subtract/{qty}")
    public ResponseEntity<String> subtractQuantity(@PathVariable String productCode, @PathVariable int qty) {
        inventoryService.updateQuantity(productCode, -qty);
        return ResponseEntity.ok("Quantity reduced successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteById(id);
        return ResponseEntity.ok("Inventory deleted");
    }



}
