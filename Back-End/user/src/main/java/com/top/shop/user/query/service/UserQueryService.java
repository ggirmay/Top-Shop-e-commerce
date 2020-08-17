package com.top.shop.user.query.service;

import com.top.shop.user.command.action.VerificationTokenCommandAction;
import com.top.shop.user.command.service.UserCommandService;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.domain.VerificationToken;
import com.top.shop.user.query.action.UserQueryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQueryService {
    @Autowired
    UserQueryAction userCommandService;
    @Autowired
    UserQueryAction userQueryAction;
    @Autowired
    VerificationTokenCommandAction vaction;

    public List<RegisteredUser> getAllUsers(){
        return userCommandService.getAllUsers();
    }

    public void deleteUser(Long id) {
        userCommandService.deleteById_reg(id);
    }

    public User getUerById(Long id) {
        return userQueryAction.getById(id);
    }

    public User getGuestUerById(Long id) {
      return   userQueryAction.getById_guest(id);
    }

    public RegisteredUser getUserBy_accountId(Long id) {
        return userQueryAction.getUserBy_accountId(id);
    }
}
