package com.top.shop.user.query.action;

import com.top.shop.user.command.service.VerificationTokenCommandService;
import com.top.shop.user.domain.GuestUser;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.exception.UserDoesntExit;
import com.top.shop.user.repository.GuestUserRepository;
import com.top.shop.user.repository.RegisteredUserRpository;
import com.top.shop.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UserQueryAction {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RegisteredUserRpository registeredUserRpository;
    @Autowired
    VerificationTokenCommandService vtc;
    RestTemplate  restTemplate = new RestTemplate();
    @Value("${shopping.cart.uri}")
    String uri;
    @Autowired
    GuestUserRepository guestUserRepository;

    public List<RegisteredUser> getAllUsers(){
        return registeredUserRpository.findAll();
    }

    public void deleteById_reg(Long id) {

        Boolean shoppingFlag;
//        shoppingFlag = restTemplate.getForObject(uri+"/shopping-cart/delete/"+id,Boolean.class);
        shoppingFlag=true;
       if(shoppingFlag==true)
           registeredUserRpository.deleteById(id);

        return;
    }

    public User getById(Long id) {
        return registeredUserRpository.findById(id).orElseThrow(()->new UserDoesntExit("Could't find user with this id"));
    }

    public User getById_guest(Long id) {
        return guestUserRepository.findById(id).orElseThrow(()->new UserDoesntExit("Could't find user with this id"));
    }

    public RegisteredUser getUserBy_accountId(Long id) {
        return registeredUserRpository.findByUserAccount(id);
    }
}
