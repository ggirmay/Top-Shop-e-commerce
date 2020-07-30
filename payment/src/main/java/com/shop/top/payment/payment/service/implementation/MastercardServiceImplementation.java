package com.shop.top.payment.payment.service.implementation;

import com.shop.top.payment.payment.exception.CardNotFoundException;
import com.shop.top.payment.payment.model.mastercard.Mastercard;
import com.shop.top.payment.payment.repository.MastercardRepository;
import com.shop.top.payment.payment.service.MastercardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MastercardServiceImplementation implements MastercardService {

    @Autowired
    private MastercardRepository mastercardRepository;

    @Override
    public List<Mastercard> getAll() {
        return this.mastercardRepository.findAll().stream()
                .filter(card -> !card.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Optional<Mastercard> getByCardNumber(String cardNumber) {
        Optional<Mastercard> card = this.mastercardRepository.findByCardNumber(cardNumber);

        if(card.isPresent() && !card.get().isDeleted()) return card;
        else return Optional.empty();
    }

    @Override
    public boolean deleteCard(String cardNumber) throws CardNotFoundException {
        Optional<Mastercard> card = this.mastercardRepository.findByCardNumber(cardNumber);

        if(card.isPresent() ) {
            if(!card.get().isDeleted())
                card.get().setDeleted();

            return true;
        }else {
            throw new CardNotFoundException(cardNumber);
        }
    }

    @Override
    public Mastercard updateCard(Mastercard card) {
        return createCard(card);
    }

    @Override
    public Mastercard createCard(Mastercard card) {
        return this.mastercardRepository.save(card);
    }
}
