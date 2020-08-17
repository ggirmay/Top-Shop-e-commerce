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

import java.net.InetAddress;
import java.net.UnknownHostException;
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
        String  ip;

        try {
           ip = InetAddress.getLocalHost().toString();
          ip = getIp(ip);
        } catch (Exception e) {
            ip="localhost";
            e.printStackTrace();
        }

        String confirmationUrl
                = event.getAppUrl() + "http://"+ip+":8086/api/verificationToken/registrationConfirm/"+userAccount.getId()+"?token=" + token;
//        String message = messages.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText( "\r\n Activate your account using this link  " + confirmationUrl);
        mailSender.send(email);
    }

    public String getIp(String host) throws Exception {
        if(host==null||host==""){
            throw  new Exception("invalid ip");
        }
        String[] s = host.split("/");
        return  s[1];
    }

}