package com.top.shop.user.command.action;

import com.top.shop.user.domain.GuestUser;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.repository.UserRepository;
import com.top.shop.user.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class UserCommandAction {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserRepository userRepository;

    public User registerUser(RegisteredUser user){
        user.getUserAccount().setPassword(passwordEncoder().encode(user.getUserAccount().getPassword()));
        user.getUserAccount().setEnabled(false);

        return userRepository.save(user);
    }

    public RegisteredUser save(RegisteredUser user) {
      return   userRepository.save(user);
    }

    public User createGuest(GuestUser guestUser) {
       return userRepository.save(guestUser);
    }
}
