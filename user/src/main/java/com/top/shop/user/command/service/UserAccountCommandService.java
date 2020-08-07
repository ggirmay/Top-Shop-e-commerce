package com.top.shop.user.command.service;

import com.top.shop.user.command.action.UserAccountCommandAction;
import com.top.shop.user.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserAccountCommandService {

    @Autowired
    UserAccountCommandAction userAccountCommandAction;

    public void enable(UserAccount userAccount){
        userAccount.enable();
        userAccountCommandAction.save(userAccount);
    }
}
