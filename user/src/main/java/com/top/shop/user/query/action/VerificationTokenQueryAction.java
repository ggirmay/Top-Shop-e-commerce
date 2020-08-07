package com.top.shop.user.query.action;

import com.top.shop.user.domain.VerificationToken;
import com.top.shop.user.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificationTokenQueryAction {
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findVerificationTokensByToken(token);
    }

}
