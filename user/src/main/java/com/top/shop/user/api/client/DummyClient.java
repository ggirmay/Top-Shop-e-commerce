package com.top.shop.user.api.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Feign client
 */
@FeignClient(name = "target-service-name-on-consul", qualifier = "dummyClient")
public class DummyClient {

}
