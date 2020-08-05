package com.shop.top.payment.payment.repository;

//import com.shop.top.payment.payment.config.security.MyUserDetails;
import com.shop.top.payment.payment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByUsername(String username);
}
