package com.shoptop.vendor.controller;

import com.shoptop.vendor.model.Role;
import com.shoptop.vendor.model.Vendor;
import com.shoptop.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping
    public ResponseEntity <?>  saveVendor (@RequestBody Vendor vendor){
        if(vendorService.findByUserName(vendor.getUserName())!= null){
            return new ResponseEntity <>(HttpStatus.CONFLICT);
        }
        vendor.setRole(Role.DATAANALYST);
        return new ResponseEntity <> (vendorService.save(vendor), HttpStatus.CREATED);
    }

    @GetMapping("/vendor/login")
    public ResponseEntity <?>  getVendor (Principal principal){
        //Principal principal = request.getUserPrincipal();
        if(principal == null || principal.getName() == null){
          // this means logout will be successful; login?logout
            return new ResponseEntity <> (HttpStatus.OK);

        }
        //return new
        return ResponseEntity.ok(vendorService.findByUserName(principal.getName()));
    }

    @PostMapping("/vendor/names")
    public ResponseEntity <?>  getNameOfVendors (@RequestBody List<Long> idList) {

        return ResponseEntity.ok(vendorService.findVendors(idList));
    }

    @GetMapping("/vendor/test")
    public ResponseEntity <String>  test(){
        return ResponseEntity.ok("It is working ... ");
    }
}
