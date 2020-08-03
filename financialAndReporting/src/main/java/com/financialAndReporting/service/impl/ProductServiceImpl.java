package com.financialAndReporting.service.impl;

import com.financialAndReporting.dao.ProductDao;
import com.financialAndReporting.models.Product;
import com.financialAndReporting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;


    @Override
    public void save(Product product) {
        productDao.save(product);

    }

    @Override
    public Product update(Product product) throws Exception {
        Product product1 = productDao.findById((long) product.getId()).get();
        if(product1==null){
            throw new Exception("No division found");
        }
        return productDao.save(product);
    }

    @Override
    public List<Product> findAll() {

        return productDao.findAll();
    }
//    @Override
//    public List<Product> findAll() {
//        final List<Product> products = new ArrayList<>();
//
//        products.add(new Product(1,"Laptop","IT0101","Dell","2300.00"));
//        products.add(new Product(2,"Cup","0987","Tes","2300.00"));
//        products.add(new Product(3,"BHHH","SSSS","fDC","2300.00"));
//        products.add(new Product(4,"hjd","qw","jh","2300.00"));
//        products.add(new Product(5,"asdfg","hjk","xdr","2300.00"));
//        products.add(new Product(6,"xcvb","mmnbg","ujm","2300.00"));
//        products.add(new Product(7,"Laptop","IT0101","nhtrds","2300.00"));
//
//        return products;
//    }

    @Override
    public Product findOne(Long id) {
        return productDao.findById(id).get();
    }
}
