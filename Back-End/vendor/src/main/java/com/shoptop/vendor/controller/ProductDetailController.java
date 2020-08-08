package com.shoptop.vendor.controller;

//import com.shop.top.vendor.model.Category;
//import com.shop.top.vendor.model.Product;
//import com.shop.top.vendor.model.ProductDetail;
//import com.shop.top.vendor.service.CategoryService;
//import com.shop.top.vendor.service.ProductDetailService;
import com.shoptop.vendor.model.ProductDetail;
import com.shoptop.vendor.service.ProductDetailService;
//import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/productDetail")
public class ProductDetailController {
    @Autowired
    ProductDetailService productDetailService;
    @GetMapping("/details")
    public @NotNull Iterable<ProductDetail> getProductDetails() {

        return productDetailService.getProductDetails();
    }
    @PostMapping(value = "/save")
    public ProductDetail addProductDetail(@RequestBody ProductDetail productDetail){
        return productDetailService.save(productDetail);
    }
    @GetMapping("/{id}")
    ProductDetail getProductDetail(@PathVariable Long id) {
        return productDetailService.getProductDetail(id);
    }
    @PutMapping("/{id}")
    ResponseEntity<ProductDetail> editProduct(@RequestBody ProductDetail newProductDetail, @PathVariable Long id) {

        ProductDetail productDetail= productDetailService.getProductDetail(id);
        productDetail.setDescription(newProductDetail.getDescription());
        productDetail.setManufacturer(newProductDetail.getManufacturer());
        final ProductDetail updatedProductDetail = productDetailService.save(productDetail);
        return ResponseEntity.ok(updatedProductDetail);
    }

    @DeleteMapping("/{id}")
    void deleteProductDetail(@PathVariable ID id) {
        productDetailService.deleteProductDetail(id);
    }
}