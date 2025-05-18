package com.shoping.productservice.service;

import com.shoping.productservice.entity.Product;
import com.shoping.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> getProductByCode(String code) {
        return productRepository.findByCode(code);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
