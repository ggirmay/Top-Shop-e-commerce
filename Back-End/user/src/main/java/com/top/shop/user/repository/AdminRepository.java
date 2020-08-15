package com.top.shop.user.repository;

import com.top.shop.user.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;

public interface AdminRepository extends JpaRepository<Admin,Long> {


}
