package com.top.shop.user.repository;

import com.top.shop.user.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.List;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query( "from Admin a inner join fetch a.account u where u.enabled = false and u.rejected=false")
    public List<Admin> getPendingAdmin();
}
