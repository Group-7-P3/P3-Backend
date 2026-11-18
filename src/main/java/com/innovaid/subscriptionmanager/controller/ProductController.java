package com.innovaid.subscriptionmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innovaid.subscriptionmanager.model.Product;
import com.innovaid.subscriptionmanager.model.ProductStatusType;
import com.innovaid.subscriptionmanager.repository.ProductRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController 
@RequestMapping("/api/product")
public class ProductController {
    
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product createProduct(){
        Product product = productRepository.createProduct();
        return product;
    }

    @PostMapping("/{serialNumber}/updateStatus")
    public void updateProductStatus(@PathVariable int serialNumber, @RequestParam ProductStatusType status){
        productRepository.updateProductStatus(serialNumber, status);
    }

}
