package com.top.shop.user.query.action;

import com.top.shop.user.domain.PaymentInformation;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.repository.RegisteredUserRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisteredUserQueryAction {
    @Autowired
    RegisteredUserRpository registeredUserRpository;
    public RegisteredUser getUserByAccount(UserAccount userAccount) {
      return   registeredUserRpository.findRegisteredUserByUserAccount(userAccount);
    }

    public List<PaymentInformation> findAllPaymetnInfo(Long id) {
        return registeredUserRpository.findAllPaymentInformationById(id);
    }
}
