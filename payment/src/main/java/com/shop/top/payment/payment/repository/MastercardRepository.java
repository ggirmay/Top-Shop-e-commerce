package com.shop.top.payment.payment.repository;

import com.shop.top.payment.payment.model.mastercard.Mastercard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MastercardRepository extends JpaRepository<Mastercard, Long> {

    public Optional<Mastercard> findByCardNumber(String cardNumber);

}
