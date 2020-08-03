package com.shoptop.vendor.service;

import com.shoptop.vendor.model.Product;
//import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.stereotype.Service;


public interface ProductService {
    Iterable<Product> getAllProducts();

    Product getProduct(long id);

    Product save(Product product);

    void 	deleteAllProducts();
    void  deleteProduct(Long id);

}
