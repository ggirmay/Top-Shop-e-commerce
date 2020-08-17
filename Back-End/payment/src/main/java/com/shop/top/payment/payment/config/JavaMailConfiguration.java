package com.shop.top.payment.payment.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailConfiguration {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("socialnetworks565@gmail.com");
        mailSender.setPassword("I1234567890mnbvcxz");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
    @Autowired
    private JavaMailSender mailSender;

    public void send(){


        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("my email");
        email.setSubject("my subject");
        email.setText( "balh blah ");
        mailSender.send(email);
    }
}
