package com.shop.top.payment.payment.exception;

public class CardNotFoundException extends Exception{

    public CardNotFoundException(String cardNumber) {
        super("****** THIS IS A CUSTOM EXCEPTION ******" + System.lineSeparator() +
                "The card with number : " + cardNumber + " does not exist in the database");
    }
}
