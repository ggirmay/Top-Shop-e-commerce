package com.top.shop.user.command.action;

import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.domain.VerificationToken;
import com.top.shop.user.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Component
public class VerificationTokenCommandAction {
    @Autowired
    VerificationTokenRepository verificationTokenRepository;


    public void save(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }

    public void remove(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }
    public void removeVerificationByAccount(UserAccount userAccount){
      VerificationToken verificationToken =  verificationTokenRepository.findVerificationTokenByUserAccount(userAccount);
        System.out.println(verificationToken.toString());
      verificationTokenRepository.delete(verificationToken);
    }
}
