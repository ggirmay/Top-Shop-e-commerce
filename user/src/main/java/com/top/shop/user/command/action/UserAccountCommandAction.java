package com.top.shop.user.command.action;

import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.domain.VerificationToken;
import com.top.shop.user.repository.UserAccountRepository;
import com.top.shop.user.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserAccountCommandAction {
    @Autowired
    UserAccountRepository userAccountRepository;


    public void save(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }
}
