package com.top.shop.user.exception;

public class UserDoesntExit extends RuntimeException {
    public UserDoesntExit(String s) {
        super(s);
    }
}
