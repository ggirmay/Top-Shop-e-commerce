package com.shoptop.vendor.vendor.controller;

import com.shoptop.vendor.vendor.model.Role;
import com.shoptop.vendor.vendor.model.Vendor;
import com.shoptop.vendor.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping
    public ResponseEntity <?>  saveVendor (@RequestBody Vendor vendor){
        if(vendorService.findByUserName(vendor.getUserName() != null)){
            return new ResponseEntity <>(HttpStatus.CONFLICT);

        }
        vendor.setRole(Role.DATAANALYST);
        return new ResponseEntity <> (vendorService.save(vendor), HttpStatus.CREATED);


    }
}
