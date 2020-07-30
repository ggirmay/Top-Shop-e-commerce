package com.shop.top.payment.payment.service;

import com.shop.top.payment.payment.exception.CardNotFoundException;
import com.shop.top.payment.payment.model.mastercard.Mastercard;

import java.util.List;
import java.util.Optional;

public interface MastercardService {

    public List<Mastercard> getAll();
    public Optional<Mastercard> getByCardNumber(String cardNumber);
    public boolean deleteCard(String cardNumber) throws CardNotFoundException;
    public Mastercard updateCard(Mastercard card);
    public Mastercard createCard(Mastercard card);

}
