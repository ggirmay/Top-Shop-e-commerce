package com.shop.top.productservice.productservice.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Book id not found : " + id);
    }

}
