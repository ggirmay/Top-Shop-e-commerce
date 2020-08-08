package com.top.shop.user.domain;


import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class CompositeKey implements Serializable {
    private String username;
    private String email;

    public CompositeKey() {
    }

    public CompositeKey(String username, String email) {
        this.username = username;
        this.email = email;
    }


}
