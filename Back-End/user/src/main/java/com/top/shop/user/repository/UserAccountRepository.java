package com.top.shop.user.repository;

import com.top.shop.user.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    public UserAccount findUserAccountByEmail(String email);
    UserAccount findUserAccountByUsername(String username);
}
