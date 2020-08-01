package com.shop.top.productservice.productservice.service;

import com.shop.top.productservice.productservice.model.ProductDetail;

public interface ProductDetailService {

    Iterable<ProductDetail> getProductDetails();

    Iterable<ProductDetail> getAllProductDetail();

    ProductDetail getProductDetail(long id);

    ProductDetail save(ProductDetail product);
}
