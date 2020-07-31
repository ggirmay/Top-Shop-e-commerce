package com.top.shop.user.domain;

import com.top.shop.user.util.Role;

public class Employee extends Vendor{
   private Role employee_role;

    public Employee(){
        super();
    }
    public Role getEmployee_role() {
        return employee_role;
    }

    public void setEmployee_role(Role employee_role) {
        this.employee_role = employee_role;
    }
}