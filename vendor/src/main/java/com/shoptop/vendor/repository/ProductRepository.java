package com.shoptop.vendor.repository;

import com.shoptop.vendor.model.Product;
//import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String productName);
    Product findById(ID id);
    Product save( Product product);
    void 	deleteAllInBatch();
    void  deleteById(Long id);
    void delete(Product product);
}
