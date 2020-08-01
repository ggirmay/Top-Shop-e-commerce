package com.shop.top.productservice.productservice.controller;

import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.service.ProductService;
import com.shop.top.productservice.productservice.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/getAll")
    public @NotNull Iterable<Product> getProducts() {

        return productService.getAllProducts();
    }
@PostMapping("/save")
    public Product addProduct( @RequestBody Product product){
     return   productService.save(product);
}
}
