package com.shop.top.payment.payment.service;

import com.shop.top.payment.payment.model.User;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    public Map<String , ?> getUserByID(long id);
    public Optional<User> loadUser(String username);

}
