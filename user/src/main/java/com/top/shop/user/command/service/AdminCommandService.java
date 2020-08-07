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
        if(validateAccountInformation(user.getAccount().getEmail())){
            return adminCommandService.registerUser(user);
        }
        else throw new UserExist("the user with this email has already exist");
    }

    public boolean validateAccountInformation(String email){
        return   userAccountQueryService.getUserAccountByEmail(email)==null?true:false;
    }

    public Admin update(Admin admin) {
       return adminCommandService.update(admin);
    }
}
