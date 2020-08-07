package com.shoptop.vendor.service;

import com.shoptop.vendor.model.Category;
public interface CategoryService {

    Iterable<Category> getAllCategory();

    Category getCategory(long id);

    Category save(Category category);

    Category findByName(String name);

    Category findById(ID id);

    void 	deleteAllInBatch();

    void  deleteCategory(Long id);

    void delete(Category category);
}