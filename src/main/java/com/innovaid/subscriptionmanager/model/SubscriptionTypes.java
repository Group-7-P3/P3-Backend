package com.innovaid.subscriptionmanager.model;

public enum SubscriptionTypes {
    HOME("home"),
    HOME_PLUS("home+"),
    PRO("pro"),
    PRO_PLUS("pro+");
    
    private final String type;

    SubscriptionTypes(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public static SubscriptionTypes getTypeFromString(String text){
        for (SubscriptionTypes value : SubscriptionTypes.values()){
            if (value.type.equals(text)){
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown subscription type: " + text);
    }
}
