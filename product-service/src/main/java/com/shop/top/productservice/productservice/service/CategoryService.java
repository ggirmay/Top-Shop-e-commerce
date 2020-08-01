package com.shop.top.productservice.productservice.service;

import com.shop.top.productservice.productservice.model.Category;

public interface CategoryService {

    Iterable<Category> getAllCategory();

    Category getCategory(long id);

    Category save(Category category);
}
