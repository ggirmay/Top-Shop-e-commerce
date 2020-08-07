package com.top.shop.user.query.service;

import com.top.shop.user.domain.Employee;
import com.top.shop.user.query.action.EmployeeQueryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeQueryService {
    @Autowired
    EmployeeQueryAction employeeQueryAction;
    public List<Employee> fndAll() {
        return employeeQueryAction.findAll();
    }

    public Employee getById(Long id) {
      return   employeeQueryAction.findBYId(id);
    }

    public List<Employee> findEmployyes(Long id) {
        return employeeQueryAction.findEmployeeByvendorId(id);
    }
}
