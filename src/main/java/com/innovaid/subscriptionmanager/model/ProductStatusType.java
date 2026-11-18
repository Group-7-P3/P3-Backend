package com.innovaid.subscriptionmanager.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductStatusType {
    INACTIVE("inactive"),
    ACTIVE("active"),
    DEACTIVATED("deactivated");

    private final String type;

    ProductStatusType(String type){
        this.type = type;
    }

    @JsonValue 
    public String getDbProductStatusType(){
        return this.type;
    }

    public static ProductStatusType getTypeFromString(String text){
        for (ProductStatusType value : ProductStatusType.values()){
            if (value.type.equals(text)){
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown product status type: " + text);
    }
}
