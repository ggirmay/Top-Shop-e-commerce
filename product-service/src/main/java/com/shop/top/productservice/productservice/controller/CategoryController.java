package com.shop.top.productservice.productservice.controller;

import com.shop.top.productservice.productservice.model.Category;
import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.service.CategoryService;
import com.shop.top.productservice.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class CategoryController {
    @Autowired
    CategoryService productService;
    @GetMapping("/categories")
    public @NotNull Iterable<Category> getCategories() {

        return productService.getAllCategory();
    }

}
