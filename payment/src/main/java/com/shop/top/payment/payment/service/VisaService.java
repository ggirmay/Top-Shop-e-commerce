package com.shop.top.payment.payment.service;

import com.shop.top.payment.payment.model.visa.Visa;
import com.shop.top.payment.payment.exception.CardNotFoundException;

import java.util.List;
import java.util.Optional;

public interface VisaService {

    public List<Visa> getAll();
    public Optional<Visa> getByCardNumber(String cardNumber);
    public boolean deleteCard(String cardNumber) throws CardNotFoundException;
    public Visa updateCard(Visa card);
    public Visa createCard(Visa card);

}
