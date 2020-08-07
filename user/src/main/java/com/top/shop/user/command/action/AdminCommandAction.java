package com.top.shop.user.command.action;
import com.top.shop.user.domain.Admin;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.repository.AdminRepository;
import com.top.shop.user.repository.UserRepository;
import com.top.shop.user.repository.VerificationTokenRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.stereotype.Component;

@Component
@Configuration
public class AdminCommandAction {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdminRepository adminRepository;

    public Admin registerUser(Admin user){
        user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
        return adminRepository.save(user);
    }

    public Admin update(Admin admin) {
        return adminRepository.save(admin);
    }
}
