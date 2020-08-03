package com.shoptop.vendor.repository;

import com.shoptop.vendor.model.Category;
//import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    List<Category> findAll();
    Category findById(ID id);
    void 	deleteAllInBatch();
    void  deleteById(Long id);
    void delete(Category category);
}
