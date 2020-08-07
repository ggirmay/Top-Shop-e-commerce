package com.top.shop.user.query.service;

import com.top.shop.user.command.service.UserCommandService;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
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

    public List<RegisteredUser> getAllUsers(){
        return userCommandService.getAllUsers();
    }

    public void deleteUser(Long id) {
        userCommandService.deleteById_reg(id);
    }

    public User getUerById(Long id) {
        return userQueryAction.getById(id);
    }
}
