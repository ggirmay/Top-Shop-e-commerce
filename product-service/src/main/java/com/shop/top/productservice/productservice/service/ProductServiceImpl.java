package com.shop.top.productservice.productservice.service;


import com.shop.top.productservice.productservice.exception.ResourceNotFoundException;
import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.model.ProductDetail;
import com.shop.top.productservice.productservice.model.ProductStatus;
import com.shop.top.productservice.productservice.repository.ProductDetailRepository;
import com.shop.top.productservice.productservice.repository.ProductRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public Product save(Product product)
        {
            return productRepository.save(product);
        }

  //@Override
//    public void 	deleteAllProducts(List<Product> products){
//       List<Product> productList=    productRepository.deleteAllInBatch(products);
//
//        for (Product p:productList
//             ) {
//            p.setStatus(ProductStatus.removed);
//        }
//    }
    @Override
    public void  deleteProduct(Long id){
     Product product=   productRepository.findById(id)
             .orElseThrow(()->new ResourceNotFoundException(id));
        product.setStatus(ProductStatus.removed);
    }

    @Override
    public void aproveProduct(Long id) {
        Product product= productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(id));
        product.setStatus(ProductStatus.approved);
    }

    @Override
    public void deAproveProduct(Long id) {
 Product product= productRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException(id));
 product.setStatus(ProductStatus.notAprroved);
    }


}