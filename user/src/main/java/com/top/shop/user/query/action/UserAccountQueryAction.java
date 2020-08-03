package com.top.shop.user.query.action;

import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAccountQueryAction {
    @Autowired
    UserAccountRepository userAccountRepository;

    public List<UserAccount> getAllAcount(){
        return userAccountRepository.findAll();
    }
    public UserAccount getUserAccount(Long id){
        return userAccountRepository.getOne(id);
    }
    public UserAccount getUserAccountByEmail(String email){
        return userAccountRepository.findUserAccountByEmail(email);
    }
}
