package com.shop.top.productservice.productservice.repository;

import com.shop.top.productservice.productservice.model.Category;
import com.shop.top.productservice.productservice.model.Product;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String productName);
    Product findById(ID id);
    Product save( Product product);
    void 	deleteAllInBatch();
    void  deleteById(Long id);
    void delete(Product product);

}
