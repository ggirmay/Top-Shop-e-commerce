package com.top.shop.user.query.action;

import com.top.shop.user.domain.Employee;
import com.top.shop.user.domain.Vendor;
import com.top.shop.user.exception.UserDoesntExit;
import com.top.shop.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeQueryAction {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    VendorQueryAction vendorQueryAction;
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findBYId(Long id) {
        return employeeRepository.findById(id).orElseThrow(()-> new UserDoesntExit("Unable to find employee with this id"));
    }

    public List<Employee> findEmployeeByvendorId(Long id) {
        Vendor vendor = vendorQueryAction.getVendorById(id);
        return employeeRepository.findEmployeeByVendor(vendor);
    }
}
