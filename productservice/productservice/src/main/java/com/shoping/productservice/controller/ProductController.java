package com.shoping.productservice.controller;

import com.shoping.productservice.entity.Product;
import com.shoping.productservice.service.InventoryClientService;
import com.shoping.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryClientService inventoryClientService;


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.getProductById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Product> getByCode(@PathVariable String code) {
        return productService.getProductByCode(code).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/{code}/availability")
    public ResponseEntity<String> checkAvailability(@PathVariable String code) {
        System.out.println("hiii");
        boolean inStock = inventoryClientService.isProductInStock(code);
        if (inStock) {
            return ResponseEntity.ok("Product is in stock");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product out of stock");
        }


    }
}


