package com.shoptop.vendor.vendor.repository;

import com.shoptop.vendor.vendor.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByUserName(String username);

    @Query("select v.name from Vendor v where v.id in (:pIdList)")
    List<String> findByIdList(@Param ("pIdList") List<Long> idList);
}
