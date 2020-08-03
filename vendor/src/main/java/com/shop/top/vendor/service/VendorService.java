package com.shop.top.vendor.service;

import com.shop.top.vendor.domain.Vendor;
import com.shop.top.vendor.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService implements IVendorService {
    @Autowired
    VendorRepository vendorRepository;

    @Override
    public List<Vendor> getAllVendor() {
        return vendorRepository.findAll();
    }
}
