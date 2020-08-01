package com.shop.top.productservice.productservice.repository;

import com.shop.top.productservice.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    List<Category> findAll();

}
