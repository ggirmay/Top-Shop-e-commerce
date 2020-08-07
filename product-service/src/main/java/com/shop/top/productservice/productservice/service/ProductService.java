package com.shop.top.productservice.productservice.service;

import com.shop.top.productservice.productservice.model.Product;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Iterable<Product> getAllProducts();

    Product getProduct(long id);

    Product save(Product product);

    //void 	deleteAllProducts();
    void  deleteProduct(Long id);
    public void aproveProduct(Long id ) ;
    public void deAproveProduct(Long id);
    List<Product> searchProduct(String prodct);
}
