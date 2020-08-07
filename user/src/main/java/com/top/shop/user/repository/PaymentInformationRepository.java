package com.top.shop.user.repository;

import com.top.shop.user.domain.PaymentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.ws.rs.QueryParam;
import java.util.Collection;
import java.util.List;

@Repository
public interface PaymentInformationRepository extends JpaRepository<PaymentInformation,Long> {
//    @Query(value = "SELECT p FROM payment_information p WHERE p.user_id = :user_id", nativeQuery = true)
//    List<PaymentInformation> findAllPaymentInformationById(@QueryParam("user_id") Long user_id);


}