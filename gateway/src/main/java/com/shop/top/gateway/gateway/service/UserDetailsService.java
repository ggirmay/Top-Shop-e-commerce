package com.shop.top.gateway.gateway.service;

import com.shop.top.gateway.gateway.api.request.UserAccount;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserAccount loadUserByEmailAndPassword(String email, String password) throws UsernameNotFoundException;
}
