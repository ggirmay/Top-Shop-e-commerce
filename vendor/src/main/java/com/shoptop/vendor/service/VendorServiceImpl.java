package com.shoptop.vendor.service;

import com.shoptop.vendor.model.Vendor;
import com.shoptop.vendor.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
//        return null;
    }


   @Override
    public Vendor findByUserName (String username){
        return vendorRepository.findByUsername(username).orElse(null);
//       return null;
    }

   @Override
    public List<String> findVendors(List<Long> idList){
        return vendorRepository.findByIdList(idList);
       //return null;
    }
}
