package com.shop.top.vendor.repository;

import com.shop.top.vendor.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
}
