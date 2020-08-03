package com.financialAndReporting.service;


import com.financialAndReporting.models.Product;

import java.util.List;

public interface ProductService {
	public void save(Product product);
	public Product update(Product product) throws Exception;
	public List<Product> findAll();
	public Product findOne(Long id);
}
