package com.shop.top.productservice.productservice.repository;

import com.shop.top.productservice.productservice.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {
    ProductDetail findByDescription(String name);
}
