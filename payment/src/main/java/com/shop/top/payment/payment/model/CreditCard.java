package com.shop.top.payment.payment.model;

public enum CreditCard {

    MASTERCARD(5 , "Mastercard" , "M"),
    VISA(4 , "Visa" , "V");

    private final int value;
    private final String issuer;
    private final String initial;

    private CreditCard(int value , String issuer , String initial){
        this.value = value;
        this.issuer = issuer;
        this.initial = initial;
    }

    public int value(){
        return this.value;
    }

    public String issuer(){
        return this.issuer;
    }

    public String initial(){
        return this.initial;
    }

}
