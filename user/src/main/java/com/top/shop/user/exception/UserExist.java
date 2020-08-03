package com.top.shop.user.exception;

public class UserExist extends RuntimeException  {

    public UserExist(String message) {
        super(message);
    }
}
