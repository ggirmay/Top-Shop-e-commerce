package com.shoptop.vendor.repository;

import com.shoptop.vendor.model.SubCategory;
//import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    SubCategory findByName(String name);
    SubCategory findById(ID id);
    void 	deleteAllInBatch();
    void  deleteById(ID id);
    void delete(SubCategory category);
}
