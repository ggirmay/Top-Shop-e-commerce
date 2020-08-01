package com.shop.top.productservice.productservice.service;

import com.shop.top.productservice.productservice.model.Product;
import org.springframework.stereotype.Service;


public interface ProductService {
    Iterable<Product> getAllProducts();

    Product getProduct(long id);

    Product save(Product product);
}
