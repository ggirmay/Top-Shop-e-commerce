package com.shoptop.vendor.service;


import com.shoptop.vendor.exception.ResourceNotFoundException;
import com.shoptop.vendor.model.Product;
import com.shoptop.vendor.repository.ProductRepository;
//import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Product save(Product product) {
        {
            return productRepository.save(product);
        }
    } @Override
    public void 	deleteAllProducts(){
        productRepository.deleteAllInBatch();
    } @Override
    public void  deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
