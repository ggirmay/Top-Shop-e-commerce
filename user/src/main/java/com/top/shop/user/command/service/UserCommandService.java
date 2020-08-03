package com.top.shop.user.command.service;

import com.top.shop.user.command.action.UserCommandAction;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.exception.UserExist;
import com.top.shop.user.query.service.UserAccountQueryService;
import com.top.shop.user.query.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {
    @Autowired
    UserCommandAction userCommandAction;
    @Autowired
    UserAccountQueryService userAccountQueryService;

    public User registerUser(RegisteredUser user){
        if(validateAccountInformation(user.getUserAccount().getEmail())){
            return userCommandAction.registerUser(user);
        }
        else throw new UserExist("the user with this email has already exist");
    }

    public boolean validateAccountInformation(String email){
      return   userAccountQueryService.getUserAccountByEmail(email)==null?true:false;
    }
}
