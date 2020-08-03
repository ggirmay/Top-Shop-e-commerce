package com.shop.top.gateway.gateway.controller;

import com.shop.top.gateway.gateway.api.cleint.UserAccountClient;
import com.shop.top.gateway.gateway.api.request.UserAccount;
import com.shop.top.gateway.gateway.api.response.AccountResponse;
import com.shop.top.gateway.gateway.config.JwtRequest;
import com.shop.top.gateway.gateway.config.JwtResponse;
import com.shop.top.gateway.gateway.util.JwtTokenUtil;
import com.shop.top.gateway.gateway.config.JwtUserDetailsService;
import com.shop.top.gateway.gateway.util.UserAccountControllerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@Configuration
public class Controller {
    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    UserAccountControllerBuilder ubuilder = new UserAccountControllerBuilder();
    UserAccountClient userAccountClient = ubuilder.getUserAccountClient();

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();

            final String baseUrl = "http://localhost:8086/api/user/login/";
            URI uri = new URI(baseUrl);

            UserAccount userAccount = new UserAccount(username,password);

            ResponseEntity<AccountResponse> result = restTemplate.postForEntity(uri, userAccount, AccountResponse.class);

            System.out.println("Result  = "+ result.getBody().getEmail());

//            AccountResponse accountResponse = userAccountClient.login(new UserAccount(username,password));

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

