package com.shoptop.vendor.controller;

import com.shoptop.vendor.model.Product;
import com.shoptop.vendor.service.ProductService;
//import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController( ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/getAll")
    public @NotNull Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }
    @PostMapping(value = "/save")
    public Product addProduct(@RequestBody Product product){
        return productService.save(product);
    }
    @GetMapping("/{id}")
    Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
    @PutMapping("/{id}")
    ResponseEntity<Product>  editProduct(@RequestBody Product newProduct, @PathVariable Long id) {

        Product p= productService.getProduct(id);
        p.setName(newProduct.getName());
        p.setCode(newProduct.getCode());
        p.setType(newProduct.getType());
        p.setPrice(newProduct.getPrice());
        p.setCategory(newProduct.getCategory());
        p.setProductDetailList(newProduct.getProductDetailList());
        p.setPictureUrl(newProduct.getPictureUrl());
        final Product updatedProduct = productService.save(p);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}