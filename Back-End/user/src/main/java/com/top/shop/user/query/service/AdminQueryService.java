package com.top.shop.user.query.service;

import com.top.shop.user.domain.Admin;
import com.top.shop.user.domain.User;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.query.action.AdminQueryAction;
import com.top.shop.user.query.action.UserQueryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminQueryService {


        @Autowired
        AdminQueryAction adminQueryAction;

        public List<Admin> getAllUsers(){
            return adminQueryAction.getAllUsers();
        }


    public List<Admin> fndAll() {
            return adminQueryAction.findAll();
    }

    public Admin getById(Long id) {
            return adminQueryAction.findAdminById(id);
    }


    public List<Admin> getPendingAdmin() {
            return adminQueryAction.getPendingAdmin();
    }
}


