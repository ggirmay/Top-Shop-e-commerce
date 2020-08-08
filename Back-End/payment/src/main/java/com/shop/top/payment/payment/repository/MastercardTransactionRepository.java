package com.shop.top.payment.payment.repository;

import com.shop.top.payment.payment.model.mastercard.MastercardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MastercardTransactionRepository extends JpaRepository<MastercardTransaction , Long> {
}
