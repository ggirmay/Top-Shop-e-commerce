package com.shop.top.payment.payment.service.implementation;

//import com.shop.top.payment.payment.config.security.MyUserDetails;
import com.shop.top.payment.payment.model.User;
import com.shop.top.payment.payment.repository.UserRepository;
import com.shop.top.payment.payment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImplementation implements UserService {

    /*

    @Autowired
    private DiscoveryClient discoveryClient;

    */

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    public Map<String , String> getUserByID(long id) {

        String urlGetStudent = "";
        ResponseEntity<String> entity = restTemplate
                .getForEntity("http://user-service/" + urlGetStudent + "?id=" + id, String.class);

        String body = entity.getBody();

        return null;
    }

    @Override
    public Optional<User> loadUser(String username) {
        return this.userRepository.findByUsername(username);
    }
}
