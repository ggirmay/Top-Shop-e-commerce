package com.top.shop.user.repository;

import com.top.shop.user.domain.Employee;
import com.top.shop.user.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findEmployeeByVendor(Vendor vendor);
}
