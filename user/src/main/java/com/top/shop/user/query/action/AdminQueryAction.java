package com.top.shop.user.query.action;

import com.top.shop.user.domain.Admin;
import com.top.shop.user.domain.User;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.exception.UserDoesntExit;
import com.top.shop.user.repository.AdminRepository;
import com.top.shop.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminQueryAction {
    @Autowired
    AdminRepository adminRepository;

    public List<Admin> getAllUsers(){
        return adminRepository.findAll();
    }


    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findAdminById(Long id) {
       return adminRepository.findById(id).orElseThrow(()->new UserDoesntExit("User  not found"));
    }
}
