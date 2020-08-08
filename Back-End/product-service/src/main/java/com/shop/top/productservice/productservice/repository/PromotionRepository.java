package com.shop.top.productservice.productservice.repository;

import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion,Long> {
    //List<Promotion> findAll();

    @Query(value = "select p from Promotion p where p.endDate > :today")
    List<Promotion> findActivePromontion(@Param("today") Date today);

}
