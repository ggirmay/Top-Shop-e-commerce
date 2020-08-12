package com.top.shop.user.repository;

import com.top.shop.user.domain.PaymentInformation;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.ws.rs.QueryParam;
import java.util.List;

@Repository
public interface RegisteredUserRpository extends JpaRepository<RegisteredUser, Long> {
    RegisteredUser findRegisteredUserByUserAccount(UserAccount userAccount);
    @Query(value = "SELECT v.paymentInformation FROM RegisteredUser v WHERE v.id = :id")
    List<PaymentInformation> findAllPaymentInformationById(@Param("id") Long id);

     @Query("from RegisteredUser r inner join fetch r.userAccount f where f.id = :id")
     RegisteredUser findByUserAccount(@QueryParam("id") Long id);
}
