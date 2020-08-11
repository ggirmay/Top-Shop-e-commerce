package com.shop.top.gateway.gateway.util;

import com.shop.top.gateway.gateway.api.request.UserAccount;

public class Resposnse {
    private UserAccount userAccount;
    private String token;

    public Resposnse(UserAccount userAccount, String token) {
        this.userAccount = userAccount;
        this.token = token;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
