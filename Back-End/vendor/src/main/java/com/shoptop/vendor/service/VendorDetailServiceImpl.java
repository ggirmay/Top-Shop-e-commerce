package com.shoptop.vendor.service;

//import com.sha.microserviceusermanagement.model.User;
//import com.sha.microserviceusermanagement.repository.UserRepository;
import com.shoptop.vendor.model.Vendor;
import com.shoptop.vendor.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class VendorDetailServiceImpl implements UserDetailsService {

        @Autowired
        private VendorRepository vendorRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Vendor user = vendorRepository.findByUsername(username).orElse(null);
            if(user == null){
                throw new UsernameNotFoundException(username);
            }
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
        }
    }