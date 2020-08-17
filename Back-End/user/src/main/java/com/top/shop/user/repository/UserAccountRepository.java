package com.top.shop.user.repository;

import com.top.shop.user.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    public UserAccount findUserAccountByEmail(String email);
    UserAccount findUserAccountByUsername(String username);
    @Transactional
    @Modifying
    @Query(value = "update UserAccount u set u.enabled=true where u.id= :id")
    public int activate(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update UserAccount u set u.enabled=false , u.rejected=true where u.id= :id")
    public int reject(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "from UserAccount u where u.enabled=true and u.rejected=false and u.Role='ADMIN'")
    List<UserAccount> findAllPendingAdmin();

}
