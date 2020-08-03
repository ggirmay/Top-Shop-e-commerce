package com.shoptop.vendor.service;

import com.shoptop.vendor.model.ProductDetail;
//import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.stereotype.Service;

@Service
public interface ProductDetailService {

    Iterable<ProductDetail> getProductDetails();

    Iterable<ProductDetail> getAllProductDetail();

    ProductDetail getProductDetail(Long id);

    ProductDetail save(ProductDetail product);

    void 	deleteAllProductDetails();

    void  deleteProductDetail(ID id);

    void deleteOneProductDetail(ProductDetail productDetail);
}