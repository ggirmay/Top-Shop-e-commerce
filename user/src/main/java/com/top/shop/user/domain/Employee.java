package com.top.shop.user.domain;

import com.top.shop.user.util.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Requirement Giver and Requirement receiver
 * @author Yome Mengistu
 */
@Entity
public class Employee{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long Id;
    private Role employee_role;

    public Employee(){

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Role getEmployee_role() {
        return employee_role;
    }

    public void setEmployee_role(Role employee_role) {
        this.employee_role = employee_role;
    }
}