package com.shoptop.vendor.controller;

import com.shoptop.vendor.model.Category;
import com.shoptop.vendor.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Locale;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
//    @GetMapping("/getAll")
//    public @NotNull Iterable<Locale.Category> getCategories() {
//
//        return categoryService.getAllCategory();
//    }
    @PostMapping(value = "/save")
    public Category addProduct(@RequestBody Category category){
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
    void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
