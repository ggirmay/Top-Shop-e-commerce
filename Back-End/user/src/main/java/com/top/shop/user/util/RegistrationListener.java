package com.top.shop.user.util;

import com.top.shop.user.command.service.UserAccountCommandService;
import com.top.shop.user.command.service.VerificationTokenCommandService;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.query.service.UserAccountQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserAccountQueryService userAccountQueryService;
    @Autowired
    VerificationTokenCommandService verificationTokenCommandService;
    @Qualifier("messageSource")
    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserAccount userAccount = event.getUserAccount();
        String token = UUID.randomUUID().toString();
        verificationTokenCommandService.createVerificationToken(userAccount,token);

        String recipientAddress = userAccount.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "http//localhost:8086/api/verificationToken/registrationConfirm/"+userAccount.getId()+"/" + token;
//        String message = messages.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText( "\r\n" + confirmationUrl);
        mailSender.send(email);
    }
}