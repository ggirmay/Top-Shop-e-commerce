package com.shop.top.payment.payment.repository;

import com.shop.top.payment.payment.model.visa.VisaTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaTransactionRepository extends JpaRepository<VisaTransaction , Long> {
}
