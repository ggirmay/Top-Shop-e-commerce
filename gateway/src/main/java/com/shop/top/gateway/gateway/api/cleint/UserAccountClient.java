package com.shop.top.gateway.gateway.api.cleint;

import com.shop.top.gateway.gateway.api.request.UserAccount;
import com.shop.top.gateway.gateway.api.response.AccountResponse;
import feign.Headers;
import feign.RequestLine;

public interface UserAccountClient {

    @RequestLine("POST /login")
    @Headers("Content-Type: application/json")
    AccountResponse login(UserAccount userAccount);

}
