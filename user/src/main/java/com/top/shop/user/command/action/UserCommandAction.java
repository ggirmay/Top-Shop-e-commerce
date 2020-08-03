package com.top.shop.user.command.action;

import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.repository.UserRepository;
import com.top.shop.user.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCommandAction {
    @Autowired
    UserRepository userRepository;

    public User registerUser(RegisteredUser user){
        user.getUserAccount().setEnabled(false);
        return userRepository.save(user);
    }
}
