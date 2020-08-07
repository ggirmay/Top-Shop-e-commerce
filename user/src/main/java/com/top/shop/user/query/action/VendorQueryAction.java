package com.top.shop.user.query.action;

import com.top.shop.user.domain.Employee;
import com.top.shop.user.domain.PaymentInformation;
import com.top.shop.user.domain.Vendor;
import com.top.shop.user.exception.UserDoesntExit;
import com.top.shop.user.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class VendorQueryAction {
    @Autowired
    VendorRepository repository;

    public Vendor getVendorById(Long id) {
        return repository.findById(id).orElseThrow(()->new UserDoesntExit("Vendor doesnt exist"));
    }

    public Vendor save(Vendor vendor) {
        new Date();
        return repository.save(vendor);
    }

    public List<Vendor> findAll() {
        return repository.findAll();
    }


    public List<PaymentInformation> findPaymentInfo(Long id) {
        return repository.findAllPaymentInformationById(id);
    }
}
