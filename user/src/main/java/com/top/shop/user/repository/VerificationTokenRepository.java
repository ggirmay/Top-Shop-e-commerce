package com.top.shop.user.repository;

import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    public VerificationToken findVerificationTokensByToken(String token);
    public VerificationToken  findVerificationTokenByUserAccount(UserAccount userAccount);
}
