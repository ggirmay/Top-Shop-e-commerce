package com.shoptop.vendor.vendor.service;

import com.shoptop.vendor.vendor.model.Vendor;
import com.shoptop.vendor.vendor.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    //we will create bean in security config
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Vendor save(Vendor vendor){
        vendor.setPassword(passwordEncoder.encode(vendor.getPassword()));
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor findByUsername (String username){
        return vendorRepository.findByUserName(username).orElse(null);
    }

    @Override
    public List<String> findUsers(List<Long> idList){
        return vendorRepository.findByIdList(idList);
    }
}
