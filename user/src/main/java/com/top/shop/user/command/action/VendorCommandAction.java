package com.top.shop.user.command.action;

import com.top.shop.user.domain.Employee;
import com.top.shop.user.domain.Vendor;
import com.top.shop.user.exception.UserDoesntExit;
import com.top.shop.user.query.service.VendorQueryService;
import com.top.shop.user.repository.EmployeeRepository;
import com.top.shop.user.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class VendorCommandAction {
    @Autowired
    VendorRepository repository;
    @Autowired
    VendorQueryService vendorQueryService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean deleteById(Long id) {
        repository.deleteById(id);
        return true;
    }


    public Vendor save(Vendor vendor) {
        vendor.getUserAccount().setPassword(passwordEncoder.encode(vendor.getUserAccount().getPassword()));
        return repository.save(vendor);
    }

    public Employee getById(Long id) {
       return employeeRepository.findById(id).orElseThrow(()->new UserDoesntExit("user not found"));
    }
}
