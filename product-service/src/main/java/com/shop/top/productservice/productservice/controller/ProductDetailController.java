package com.shop.top.productservice.productservice.controller;

import com.shop.top.productservice.productservice.model.Category;
import com.shop.top.productservice.productservice.model.ProductDetail;
import com.shop.top.productservice.productservice.service.CategoryService;
import com.shop.top.productservice.productservice.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class ProductDetailController {
    @Autowired
    ProductDetailService productDetailService;
    @GetMapping("/details")
    public @NotNull Iterable<ProductDetail> getProductDetails() {

        return productDetailService.getProductDetails();
    }
}
