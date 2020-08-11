package com.shop.top.productservice.productservice.service;

import com.shop.top.productservice.productservice.exception.ResourceNotFoundException;
import com.shop.top.productservice.productservice.model.Category;
import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.repository.CategoryRepository;
import com.shop.top.productservice.productservice.repository.ProductRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements  CategoryService {

   private final CategoryRepository categoryRepository;
   @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository)
    {
        this.categoryRepository=categoryRepository;
    }
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
    @Override
   public Category findByName(String name){

        return categoryRepository.findByName(name);
    }
    @Override
   public Category findById(ID id){
       return categoryRepository.findById(id);
    }
    @Override
    public void 	deleteAllInBatch(){
        categoryRepository.deleteAllInBatch();

    }
    @Override
    public void  deleteCategory(ID id){
        categoryRepository.deleteById(id);

    }
    @Override
    public void delete(Category category){

        categoryRepository.delete(category);
    }
}
