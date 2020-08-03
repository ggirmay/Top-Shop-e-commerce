package com.shop.top.payment.payment.service;

import com.shop.top.payment.payment.exception.CardCreationException;
import com.shop.top.payment.payment.exception.CardNotFoundException;
import com.shop.top.payment.payment.model.mastercard.Mastercard;
import com.shop.top.payment.payment.model.mastercard.MastercardTransaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MastercardService {

    public List<Mastercard> getAll();
    public Optional<Mastercard> getByCardNumber(String cardNumber);
    public boolean deleteCard(String cardNumber) throws CardNotFoundException;
    public Mastercard updateCard(Mastercard card);
    public Mastercard saveCard(Mastercard card);
    public Mastercard createCard(String lastName , String firstName , String nameOnCard) throws CardCreationException;
    public /*MastercardTransaction*/ boolean checkout(String cardNumber , String nameOnCard , String securityDigit, LocalDate expirationDate, double amount);
    public /*MastercardTransaction*/ boolean pay(Mastercard mastercard, double amount);


}
