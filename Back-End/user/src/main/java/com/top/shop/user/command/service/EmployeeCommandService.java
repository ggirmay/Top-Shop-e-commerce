package com.top.shop.user.command.service;

import com.netflix.discovery.converters.Auto;
import com.top.shop.user.domain.Admin;
import com.top.shop.user.domain.Employee;
import com.top.shop.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCommandService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeCommandService employeeCommandService;


    public Employee registerUser(Employee employee) {
        if(!employee.getRole().equals("ADMIN")) {
            employee.getUserAccount().setRole(employee.getRole());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public Employee update(Employee employee){
       return employeeRepository.save(employee);
    }

    public Boolean deleteEmployee(Long id) {
         employeeRepository.deleteById(id);
        Employee employee = employeeRepository.findById(id).get();
        if(employee==null) return true;
        return false;
    }
}
