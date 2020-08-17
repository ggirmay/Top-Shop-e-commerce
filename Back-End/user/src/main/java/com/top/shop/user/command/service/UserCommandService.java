package com.top.shop.user.command.service;

import com.top.shop.user.command.action.UserCommandAction;
import com.top.shop.user.domain.GuestUser;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.exception.UserExist;
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
            User temporary = userCommandAction.registerUser(user);

            // this part for calling API in shopping cart module to create a shopping cart when use register.
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HashMap<String, Long> shoppingCart = new HashMap<>();
            shoppingCart.put("userId", temporary.getId());

            HttpEntity<HashMap<String , Long >> request = new HttpEntity(shoppingCart, httpHeaders);
            try {
                restTemplate.postForObject("http://localhost:8080/shopping-cart-service/shoppingcart/createnewcart", request, shoppingCart.getClass());
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
