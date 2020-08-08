package com.top.shop.user.query.service;

import com.top.shop.user.domain.VerificationToken;
import com.top.shop.user.query.action.VerificationTokenQueryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenQueryService {
    @Autowired

    VerificationTokenQueryAction verificationTokenQueryAction;

    public VerificationToken getVerificationToken(String token){
        return verificationTokenQueryAction.getVerificationToken(token);
    }
}
