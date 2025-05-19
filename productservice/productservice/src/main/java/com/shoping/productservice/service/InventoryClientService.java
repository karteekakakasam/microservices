package com.shoping.productservice.service;

import com.shoping.productservice.dto.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Component
public class InventoryClientService {
    @Autowired
    RestTemplate restTemplate;

    private final String INVENTORY_SERVICE_URL = "http://localhost:8082/inventory/";

    public boolean isProductInStock(String productCode) {
        try {
            ResponseEntity<Inventory> response = restTemplate.getForEntity(INVENTORY_SERVICE_URL + productCode, Inventory.class);
            Inventory inventory = response.getBody();
            return inventory != null && inventory.getAvailableQuantity() > 0;

        } catch (Exception e) {
            // Log error and treat as no stock
            return false;
        }

    }
}