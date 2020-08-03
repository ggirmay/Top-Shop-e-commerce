package com.shoptop.vendor.service;

import com.shoptop.vendor.exception.ResourceNotFoundException;
import com.shoptop.vendor.model.ProductDetail;
import com.shoptop.vendor.repository.ProductDetailRepository;
//import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailImpl implements ProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public Iterable<ProductDetail> getProductDetails() {
        return null;
    }

    @Override
    public Iterable<ProductDetail> getAllProductDetail() {
        return productDetailRepository.findAll();
    }

    @Override
    public ProductDetail getProductDetail(Long id) {
        return productDetailRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }
    @Override
    public void deleteAllProductDetails(){
        productDetailRepository.deleteAllInBatch();
    }
    @Override
    public void  deleteProductDetail(ID id){
        productDetailRepository.deleteById(id);
    }
    @Override
    public void deleteOneProductDetail(ProductDetail productDetail){
        productDetailRepository.delete(productDetail);
    }
}
