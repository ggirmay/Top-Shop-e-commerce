package com.shop.top.gateway.gateway.service;


import com.shop.top.gateway.gateway.util.MyUserDetails;
import com.shop.top.gateway.gateway.api.request.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(username + "User name");

        final String baseUrl = "http://localhost:8086/api/user/findByUserName/"+username;
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        UserAccount user  = restTemplate.getForObject(uri, UserAccount.class);

        if (user!=null) {
            System.out.println(user.toString()+ "user Service main");
            List role = new ArrayList<>();
            GrantedAuthority auth = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return user.getRole();
                }
            };
           List gAtuh = new ArrayList<>();
           gAtuh.add(auth);

            return new User(user.getUsername(), user.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_"+user.getRole())));

        } else {
            throw new UsernameNotFoundException("uer ot found");
        }

    }
}
