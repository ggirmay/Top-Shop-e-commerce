package com.shop.top.payment.payment.service.implementation;

import com.shop.top.payment.payment.model.visa.Visa;
import com.shop.top.payment.payment.exception.CardNotFoundException;
import com.shop.top.payment.payment.repository.VisaRepository;
import com.shop.top.payment.payment.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisaServiceImplementation implements VisaService {

    @Autowired
    private VisaRepository visaRepository;

    @Override
    public List<Visa> getAll() {
        return this.visaRepository.findAll().stream()
                .filter(card -> !card.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Optional<Visa> getByCardNumber(String cardNumber) {
        Optional<Visa> card = this.visaRepository.findByCardNumber(cardNumber);

        if(card.isPresent() && !card.get().isDeleted()) return card;
        else return Optional.empty();
    }

    @Override
    public boolean deleteCard(String cardNumber) throws CardNotFoundException {
        Optional<Visa> card = this.visaRepository.findByCardNumber(cardNumber);

        if(card.isPresent() ) {
            if(!card.get().isDeleted())
                card.get().setDeleted();

            return true;
        }else {
            throw new CardNotFoundException(cardNumber);
        }

    }

    @Override
    public Visa updateCard(Visa card) {
        return createCard(card);
    }

    @Override
    public Visa createCard(Visa card) {
        return this.visaRepository.save(card);
    }
}
