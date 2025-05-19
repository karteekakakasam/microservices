package com.shoping.inventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Inventory {
    @Id
    private Long id;

    private String productCode;

    private Integer availableQuantity = 0;
}

