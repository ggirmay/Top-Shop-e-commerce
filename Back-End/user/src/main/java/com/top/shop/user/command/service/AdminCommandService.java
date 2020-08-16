package com.top.shop.user.command.service;

import com.top.shop.user.command.action.AdminCommandAction;
import com.top.shop.user.command.action.UserCommandAction;
import com.top.shop.user.domain.Admin;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.exception.UserExist;
import com.top.shop.user.query.service.UserAccountQueryService;
import com.top.shop.user.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCommandService {
    @Autowired
    AdminCommandAction adminCommandService;
    @Autowired
    UserAccountQueryService userAccountQueryService;

    public Admin registerUser(Admin user){
        if(userAccountQueryService.validateAccountInformation(user.getAccount().getEmail(),user.getAccount().getUsername())){
            user.getAccount().setRole("ADMIN");
            return adminCommandService.registerUser(user);
        }
        else throw new UserExist("User exist with this email or username");
    }



    public Admin update(Admin admin) {
       return adminCommandService.update(admin);
    }

    public boolean activate(Long id) {
      return   adminCommandService.activate(id);
    }

    public boolean reject(Long id) {
        return   adminCommandService.reject(id);
    }

    public boolean delete(Long id) {
        return adminCommandService.delete(id);
    }
}
