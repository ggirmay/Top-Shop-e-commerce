package com.shoptop.vendor.service;

import com.shoptop.vendor.model.Vendor;

import java.util.List;

public interface VendorService {

    Vendor save(Vendor user);

    Vendor findByUserName(String usernname);

    List<String> findVendors(List<Long> idList);
}
