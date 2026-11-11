package com.innovaid.subscriptionmanager.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SubscriptionType {
    HOME("home"),
    HOME_PLUS("home+"),
    PRO("pro"),
    PRO_PLUS("pro+");
    
    private final String type;

    SubscriptionType(String type){
        this.type = type;
    }

    @JsonValue // For Spring til at bruge funktionen når 
    public String getDbTypeValue(){
        return this.type;
    }

    public static SubscriptionType getTypeFromString(String text){
        for (SubscriptionType value : SubscriptionType.values()){
            if (value.type.equals(text)){
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown subscription type: " + text);
    }

    
}
