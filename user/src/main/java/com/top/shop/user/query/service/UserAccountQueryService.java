package com.top.shop.user.query.service;

import com.top.shop.user.api.response.LoginResponse;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.query.action.UserAccountQueryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
