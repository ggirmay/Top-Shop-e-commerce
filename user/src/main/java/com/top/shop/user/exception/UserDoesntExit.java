package com.top.shop.user.exception;

import java.util.function.Supplier;

public class UserDoesntExit extends RuntimeException {
    public UserDoesntExit(String s) {
        super(s);
    }
}
