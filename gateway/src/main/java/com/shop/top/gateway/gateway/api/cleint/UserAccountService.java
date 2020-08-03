package com.shop.top.gateway.gateway.api.cleint;

import com.shop.top.gateway.gateway.api.request.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAccountService {
        UserAccount loadUserByEmailAndPassword(String email, String password) throws UsernameNotFoundException;
    }