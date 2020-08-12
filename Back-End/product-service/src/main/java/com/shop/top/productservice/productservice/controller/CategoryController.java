package com.shop.top.productservice.productservice.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.shop.top.productservice.productservice.model.Category;
import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.service.CategoryService;
import com.shop.top.productservice.productservice.service.ProductService;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/getAll")
    public @NotNull Iterable<Category> getCategories() {

        return categoryService.getAllCategory();
    }
    @PostMapping(value = "/save")
    public Category addCategory(@RequestBody Category category){
        System.out.println(category.getName());
        return categoryService.save(category);
    }
    @GetMapping("/{id}")
    Category getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }
    @PutMapping("/{id}")
    ResponseEntity<Category> editProduct(@RequestBody Category newCategry, @PathVariable Long id) {
        Category cat= categoryService.getCategory(id);
        cat.setName(newCategry.getName());

        final Category updatedCategory = categoryService.save(cat);
        return ResponseEntity.ok(updatedCategory);
    }
    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable ID id) {
        categoryService.deleteCategory(id);
    }
}
