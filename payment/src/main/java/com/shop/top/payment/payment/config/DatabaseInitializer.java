package com.shop.top.payment.payment.config;

import com.shop.top.payment.payment.model.Role;
import com.shop.top.payment.payment.model.User;
import com.shop.top.payment.payment.repository.RoleRepository;
import com.shop.top.payment.payment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "initialize-db" , havingValue = "Yes")
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role roleUser = new Role("USER");
        roleUser = this.roleRepository.save(roleUser);

        Role roleAdmin = new Role("ADMIN");
        roleAdmin = this.roleRepository.save(roleAdmin);

        User user = new User("Ny Aina Mickael" , "Andriantsoa" , "Admin", passwordEncoder.encode("admin") , roleAdmin);
        this.userRepository.save(user);

        user = new User("user" , "user" , "user" , passwordEncoder.encode("user") , roleUser);
        this.userRepository.save(user);

    }


}
