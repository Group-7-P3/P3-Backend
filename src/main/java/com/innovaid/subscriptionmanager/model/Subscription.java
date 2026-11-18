package com.innovaid.subscriptionmanager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


// Dummy data
public class Subscription{

    private int licenseKey;
    private int userId;
    private SubscriptionType type;
    private int maxDevices;
    private LocalDateTime createdAt; // 
    private LocalDateTime updatedAt;
    private LocalDate expiresAt;
    private boolean autoRenew;

    Subscription(
        int licenseKey,
        int userId,
        SubscriptionType type,
        int maxDevices,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDate expiresAt,
        boolean autoRenew
    ){
        this.licenseKey = licenseKey;
        this.userId = userId;
        this.type = type;
        this.maxDevices = maxDevices;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
        this.autoRenew = autoRenew;
    }

    // Parameter-less constructor for setting values with spring
    public Subscription(){}

    // Getter functions to keep fields private;

    public int getLicenseKey(){
        return this.licenseKey;
    }

    public int getUserId(){
        return this.userId;
    }

    public SubscriptionType getType(){
        return this.type;
    }

    public int getMaxDevices(){
        return this.maxDevices;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }

    public LocalDate getExpiresAt(){
        return this.expiresAt;
    }

    public boolean isAutoRenew(){
        return this.autoRenew;
    }

    // Setter functions to keep fields private;

    public void setLicenseKey(int licenseKey){
        this.licenseKey = licenseKey;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public void setType(SubscriptionType type){
        this.type = type;
    }

    public void setMaxDevices(int maxDevices){
        this.maxDevices = maxDevices;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }

    public void setExpiresAt(LocalDate expiresAt){
        this.expiresAt = expiresAt;
    }

    public void setAutoRenew(boolean autoRenew){
        this.autoRenew = autoRenew;
    }

}

