package com.shop.top.gateway.gateway.api.server;

import com.shop.top.gateway.gateway.api.cleint.UserAccountClient;
import com.shop.top.gateway.gateway.api.request.UserAccount;
import com.shop.top.gateway.gateway.api.response.AccountResponse;
import com.shop.top.gateway.gateway.config.JwtRequest;
import com.shop.top.gateway.gateway.config.JwtResponse;
import com.shop.top.gateway.gateway.service.UserDetailsServiceImpl;
import com.shop.top.gateway.gateway.util.JwtTokenUtil;
import com.shop.top.gateway.gateway.config.JwtUserDetailsService;
import com.shop.top.gateway.gateway.util.Resposnse;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@Configuration
@CrossOrigin(origins = "*",allowedHeaders = "*")
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
    private UserDetailsServiceImpl userDetailsService;

    UserAccountControllerBuilder ubuilder = new UserAccountControllerBuilder();
    UserAccountClient userAccountClient = ubuilder.getUserAccountClient();

    @PostMapping("/authenticate")
    public ResponseEntity<Resposnse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword(),authenticationRequest.getEmail());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(userDetailsService.userAccount);
        return ResponseEntity.ok( new Resposnse(userDetailsService.userAccount,token));
    }

    private void authenticate(String username, String password, String email) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

