package com.shop.top.vendor.controller;

import com.shop.top.vendor.domain.Vendor;
import com.shop.top.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @GetMapping("/ping")
    public String ping(){
        return "ok";
    }
    @GetMapping
    public ResponseEntity<List<Vendor>> getAll(){
        return ResponseEntity.ok().body(vendorService.getAllVendor());
    }
}
