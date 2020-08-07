package com.top.shop.user.command.service;

import com.top.shop.user.command.action.VerificationTokenCommandAction;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.domain.VerificationToken;
import com.top.shop.user.exception.UserDoesntExit;
import com.top.shop.user.query.service.UserAccountQueryService;
import com.top.shop.user.query.service.UserQueryService;
import com.top.shop.user.util.OnRegistrationCompleteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VerificationTokenCommandService {

    @Autowired
    VerificationTokenCommandAction verificationTokenCommandAction;
    @Autowired
    UserQueryService userQueryService;
    @Autowired
    UserAccountQueryService userAccountQueryService;
    @Autowired
    ApplicationEventPublisher eventPublisher;
    final Logger log = LoggerFactory.getLogger(VerificationTokenCommandService.class);
    public void createVerificationToken(UserAccount userAccount, String token) {
        VerificationToken verificationToken = new VerificationToken(token,userAccount);
        verificationTokenCommandAction.save(verificationToken);
    }

    public void remove(VerificationToken verificationToken) {
        verificationTokenCommandAction.remove(verificationToken);
    }
    public boolean resendActivation(String email) {
       UserAccount userAccount = userAccountQueryService.getUserAccountByEmail(email);
    if(userAccount==null) throw new UserDoesntExit("We dont find this user using this email");
    else {
        verificationTokenCommandAction.removeVerificationByAccount(userAccount);
        try {
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userAccount,
                    null, null));
        } catch (RuntimeException ex){
            log.error(ex.toString());
            ex.printStackTrace();
            return false;
        }

    }

    return true;
    }
}
