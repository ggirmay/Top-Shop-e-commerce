package com.shop.top.productservice.productservice.repository;

import com.shop.top.productservice.productservice.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    SubCategory findByName(String name);
}
