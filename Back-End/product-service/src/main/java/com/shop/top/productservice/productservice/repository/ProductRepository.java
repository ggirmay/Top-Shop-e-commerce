package com.shop.top.productservice.productservice.repository;

import com.shop.top.productservice.productservice.model.Category;
import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.model.ProductStatus;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String productName);
    //like %?1%
    @Query("select p from Product p  where p.name like %?1% ")
    Page<Product> findProdcutByName(String productName, Pageable page);
    Product findById(ID id);
    Product save( Product product);
    void 	deleteAllInBatch();
    void  deleteById(Long id);
    void delete(Product product);

    @Query(value = "select p from Product p  where p.status = :status ")
    List<Product> getpending(@Param(value = "status") ProductStatus productStatus);
}
