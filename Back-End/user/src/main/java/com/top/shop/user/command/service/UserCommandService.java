package com.top.shop.user.command.service;

import com.top.shop.user.command.action.UserCommandAction;
import com.top.shop.user.domain.GuestUser;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.exception.UserExist;
import com.top.shop.user.query.service.RegisterduserQueryService;
import com.top.shop.user.query.service.UserAccountQueryService;
import com.top.shop.user.query.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class UserCommandService {
    @Autowired
    UserCommandAction userCommandAction;
    @Autowired
    UserAccountQueryService userAccountQueryService;
  
    @Autowired
    RestTemplate restTemplate;

    public User registerUser(RegisteredUser user){
        if(userAccountQueryService.validateAccountInformation(user.getUserAccount().getEmail(),user.getUserAccount().getUsername())){
            RegisteredUser temporary = userCommandAction.registerREgisteredUser(user);

            // this part for calling API in shopping cart module to create a shopping cart when use register.
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HashMap<String, Long> shoppingCart = new HashMap<>();
            shoppingCart.put("userId", temporary.getUserAccount().getId());

            HttpEntity<HashMap<String , Long >> request = new HttpEntity(shoppingCart, httpHeaders);
            try {
            	System.out.println("BEFORE CALLING CREATE SHOPPING CART");
                restTemplate.postForObject("http://localhost:8087/shoppingcart/createnewcart", request, shoppingCart.getClass());
                System.out.println("AFTER CALLING CREATE SHOPPING CART");
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return temporary;
        }
        else throw new UserExist("the user with this email/username has already exist");
    }




    public RegisteredUser update(RegisteredUser registeredUser) {
        return userCommandAction.save(registeredUser);
    }

    public User createGuest(GuestUser guestUser) {
        return userCommandAction.createGuest(guestUser);
    }


}
