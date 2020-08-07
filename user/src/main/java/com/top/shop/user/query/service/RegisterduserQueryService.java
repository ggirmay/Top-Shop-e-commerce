package com.top.shop.user.query.service;

import com.top.shop.user.domain.PaymentInformation;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.query.action.RegisteredUserQueryAction;
import com.top.shop.user.query.action.UserAccountQueryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RegisterduserQueryService {
    @Autowired
    RegisteredUserQueryAction userQuery;


    public RegisteredUser finsByUsername(UserAccount userAccount) {
        return userQuery.getUserByAccount(userAccount);
    }

    public List<PaymentInformation> findAllPaymetnInformation(Long id) {

        return userQuery.findAllPaymetnInfo(id);
    }


}
