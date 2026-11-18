package com.innovaid.subscriptionmanager.model;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

public class Product {
    private int serialNumber;
    private ProductStatusType statusType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    Product(
        int serialNumber,
        ProductStatusType statusType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ){
        this.serialNumber = serialNumber;
        this.statusType = statusType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product(){};

    public int getSerialNumber(){
        return this.serialNumber;
    }

    public ProductStatusType getStatusType(){
        return this.statusType;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }

    public void setSerialNumber(int serialNumber){
        this.serialNumber = serialNumber;
    }

    public void setStatusType(ProductStatusType statusType){
        this.statusType = statusType;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }
}
