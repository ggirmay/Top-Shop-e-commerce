package com.top.shop.user.query.service;

import com.top.shop.user.api.response.LoginResponse;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.query.action.UserAccountQueryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAccountQueryService {
    @Autowired
    UserAccountQueryAction userAccountQueryAction;

    public UserAccount getUserAccountByEmail(String email){
      return   userAccountQueryAction.getUserAccountByEmail(email);
    }

    public LoginResponse authenticate(String email, String password) {
        UserAccount userAccount = userAccountQueryAction.getUserAccountByEmail(email);
        if(userAccount!=null){
            if(userAccount.getPassword().equals(password)){
                return new LoginResponse(userAccount.getId(),userAccount.getEmail(),"USER","MOck");
            }
        }
        return null;
    }

    public UserAccount finsByUsername(String userName) {
        return userAccountQueryAction.getUserAccountByUserName(userName);
    }

    public boolean validateAccountInformation(String email,String username){
        boolean u = finsByUsername(username)==null?true:false;
        boolean e = getUserAccountByEmail(email)==null?true:false;

        return u&&e;
    }

    public List<UserAccount> getAllPendingAdmins() {
       return userAccountQueryAction.getAllPendingAdmins();
    }
}
