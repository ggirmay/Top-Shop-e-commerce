package com.shop.top.payment.payment.service;

import com.shop.top.payment.payment.exception.CardCreationException;
import com.shop.top.payment.payment.model.visa.Visa;
import com.shop.top.payment.payment.exception.CardNotFoundException;
import com.shop.top.payment.payment.model.visa.VisaTransaction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface VisaService {

    public List<Visa> getAll();
    public Optional<Visa> getByCardNumber(String cardNumber);
    public boolean deleteCard(String cardNumber) throws CardNotFoundException;
    public Visa updateCard(Visa card);
    public Visa saveCard(Visa card);
    public Visa createCard(String lastName , String firstName , String nameOnCard) throws CardCreationException;
    public /*VisaTransaction*/ /*boolean*/ HashMap<String , Boolean> checkout(String cardNumber , String nameOnCard , String securityDigit, LocalDate expirationDate , double amount);
    public /*VisaTransaction*/ boolean pay(Visa card, double amount);
}
