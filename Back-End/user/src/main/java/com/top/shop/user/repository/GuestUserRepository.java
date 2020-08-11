package com.top.shop.user.repository;

import com.top.shop.user.domain.GuestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestUserRepository extends JpaRepository<GuestUser,Long> {
}
