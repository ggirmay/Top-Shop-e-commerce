package com.top.shop.user.api.server;

import com.top.shop.user.command.service.UserAccountCommandService;
import com.top.shop.user.command.service.VerificationTokenCommandService;
import com.top.shop.user.domain.User;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.domain.VerificationToken;
import com.top.shop.user.query.service.VerificationTokenQueryService;
import com.top.shop.user.repository.VerificationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Locale;

@RestController
@RequestMapping("/api/verificationToken")
public class VerificationTokenApi{

    @Autowired
    VerificationTokenQueryService verificationTokenQueryService;
    @Autowired
    VerificationTokenCommandService verificationTokenCommandService;
    @Qualifier("messageSource")
    @Autowired
    private MessageSource messages;
    Locale locale;
    @Autowired
    UserAccountCommandService userAccountCommandService;
    final Logger log = LoggerFactory.getLogger(VerificationTokenApi.class);

    @GetMapping("/registrationConfirm/{id}")
    public ResponseEntity<String> confirmRegistration(@PathVariable Long id, @RequestParam String token){
        log.debug("id = " +id+" token =" + token );


        VerificationToken verificationToken = verificationTokenQueryService.getVerificationToken(token);

        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, null);
            return ResponseEntity.ok().body(message);
        }

        UserAccount userAccount = verificationToken.getUserAccount();
        log.debug(userAccount.getId()+" "+ id);
        if(!userAccount.getId().equals(id)){
            String message = messages.getMessage("auth.invalid.id", null, null);
            return ResponseEntity.ok().body("User doesnt exist");
        }
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String message = messages.getMessage("auth.message.expired", null, null);
            return ResponseEntity.ok().body(message);
        }

        userAccount.setEnabled(true);

        userAccountCommandService.enable(userAccount);
        verificationTokenCommandService.remove(verificationToken);

//        log.debug("user Account = " + userAccount );


        return ResponseEntity.ok().body("account verified");
    }
    @PostMapping(value = "/resendActivation/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> resendActivation(@PathVariable String email){
       return verificationTokenCommandService.resendActivation(email)?ResponseEntity.ok().body("Verification token sent"):ResponseEntity.ok().body("Sorry");
    }

}
