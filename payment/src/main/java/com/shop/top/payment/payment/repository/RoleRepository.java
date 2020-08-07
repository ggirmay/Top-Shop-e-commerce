package com.shop.top.payment.payment.repository;

import com.shop.top.payment.payment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long> {
}
