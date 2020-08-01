package com.shop.top.productservice.productservice.service;

import com.shop.top.productservice.productservice.model.Category;
import com.sun.xml.bind.v2.model.core.ID;

public interface CategoryService {

    Iterable<Category> getAllCategory();

    Category getCategory(long id);

    Category save(Category category);

    Category findByName(String name);

    Category findById(ID id);

    void 	deleteAllInBatch();

    void  deleteById(ID id);

    void delete(Category category);
}
