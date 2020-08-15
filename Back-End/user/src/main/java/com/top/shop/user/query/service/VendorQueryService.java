package com.top.shop.user.query.service;

import com.top.shop.user.domain.Vendor;
import com.top.shop.user.query.action.VendorQueryAction;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorQueryService {

    @Autowired
    private VendorQueryAction action;
    final Log log = LogFactory.getLog(VendorQueryService.class);

    public Vendor updateVendor(Vendor vendor){
        Vendor c = action.save(vendor);
        log.info("Create Vendor status: {},"+c.toString());
        return c;
    }

    public List<Vendor> findAllVendor(){
        return action.findAll();
    }

    public Vendor getVendorById(Long id){
        return action.getVendorById(id);
    }


    public List<Vendor> getpending() {
        return  action.getPendingBVendors();
    }

}
