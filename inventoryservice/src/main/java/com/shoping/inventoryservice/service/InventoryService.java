package com.shoping.inventoryservice.service;

import com.shoping.inventoryservice.entity.Inventory;
import com.shoping.inventoryservice.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepository;

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getByProductCode(String code) {
        return inventoryRepository.findByProductCode(code);
    }

    public boolean isInStock(String code, int requiredQty) {
        return inventoryRepository.findByProductCode(code)
                .map(inv -> inv.getAvailableQuantity() >= requiredQty)
                .orElse(false);
    }

    public void updateQuantity(String code, int deltaQty) {
        inventoryRepository.findByProductCode(code).ifPresent(inv -> {
            inv.setAvailableQuantity(inv.getAvailableQuantity() + deltaQty);
            inventoryRepository.save(inv);
        });
    }

    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }
}
