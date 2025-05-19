package com.shoping.productservice.controller;

import com.shoping.productservice.entity.Product;
import com.shoping.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

@Autowired
private ProductService productService;



    //get all productss

    @GetMapping
    public List<Product >getAllProucts(){
        return productService.getAllProducts();
    }

    //get product by id
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }


    //put ( create ) product

    @PostMapping
    public  Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);

    }

    //put ( update ) product

    //delete product by id





}
