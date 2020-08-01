package com.shop.top.productservice.productservice.service;

import com.shop.top.productservice.productservice.exception.ResourceNotFoundException;
import com.shop.top.productservice.productservice.model.Category;
import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.repository.CategoryRepository;
import com.shop.top.productservice.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements  CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Iterable<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    @Override
    public Category getCategory(long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
