package com.top.shop.user.query.action;

import com.top.shop.user.domain.User;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.exception.BadInputRequestException;
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

    public UserAccount getUserAccountByUserName(String userName) {
       UserAccount userAccount = userAccountRepository.findUserAccountByUsername(userName);
       if(userAccount==null)
           return null;
//        throw new BadInputRequestException("Couldn't find user with this account");
        else return userAccount;
    }

    public List<UserAccount> getAllPendingAdmins() {
        return userAccountRepository.findAllPendingAdmin();
    }
}
