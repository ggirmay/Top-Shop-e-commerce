package com.shop.top.payment.payment.repository;

import com.shop.top.payment.payment.model.visa.Visa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisaRepository extends JpaRepository<Visa, Long> {

    public Optional<Visa> findByCardNumber(String cardNumber);

}
