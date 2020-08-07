package com.shop.top.gateway.gateway.api.cleint;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class UserAccountControllerBuilder {
    private UserAccountClient userAccountClient = createClient(UserAccountClient.class, "http://localhost:8086/api/user/");

    public UserAccountClient getUserAccountClient() {
        return userAccountClient;
    }

    private static <T> T createClient(Class<T> type, String uri) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(type))
                .logLevel(Logger.Level.FULL)
                .target(type, uri);

    }

    public UserAccountControllerBuilder() {
    }
}
