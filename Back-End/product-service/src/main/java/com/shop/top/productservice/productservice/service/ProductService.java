package com.shop.top.productservice.productservice.service;

import com.shop.top.productservice.productservice.model.Product;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<Product> getAllProducts();

    Product getProduct(long id);

    Product save(Product product);

    //void 	deleteAllProducts();
    void  deleteProduct(Long id);
    public void aproveProduct(Long id ) ;
    public void deAproveProduct(Long id);
    Page<Product> searchProduct(String prodct, Pageable pageable);
}
