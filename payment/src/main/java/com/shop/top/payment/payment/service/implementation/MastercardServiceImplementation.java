package com.shop.top.payment.payment.service.implementation;

import com.shop.top.payment.payment.exception.CardCreationException;
import com.shop.top.payment.payment.exception.CardNotFoundException;
import com.shop.top.payment.payment.model.CreditCard;
import com.shop.top.payment.payment.model.mastercard.Mastercard;
import com.shop.top.payment.payment.model.mastercard.MastercardTransaction;
import com.shop.top.payment.payment.repository.MastercardRepository;
import com.shop.top.payment.payment.service.MastercardService;
import com.shop.top.payment.payment.utils.Comparator;
import com.shop.top.payment.payment.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
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
            if(!card.get().isDeleted()) {
                card.get().setDeleted();
                this.mastercardRepository.save(card.get());
            }
            return true;
        }else {
            throw new CardNotFoundException(cardNumber);
        }
    }

    @Override
    public Mastercard updateCard(Mastercard card) {
        return saveCard(card);
    }

    @Override
    public Mastercard createCard(String lastName , String firstName , String nameOnCard) throws CardCreationException{

        if (nameOnCard.equalsIgnoreCase("")){
            throw new CardCreationException("Name on card cannot be Empty");
        }

        if (lastName.equalsIgnoreCase("") && firstName.equalsIgnoreCase(""))
            throw new CardCreationException("A card must contains at least a Lastname or a Firstname");

        String cardNumber = Generator.generateCreditCardNumber(CreditCard.MASTERCARD.value());
        Optional<Mastercard> card = this.getByCardNumber(cardNumber);

        while(card.isPresent()){
            cardNumber = Generator.generateCreditCardNumber(CreditCard.MASTERCARD.value());
            card = this.getByCardNumber(cardNumber);
        }

        return new Mastercard(lastName, firstName, nameOnCard , cardNumber);
    }

    @Override
    public boolean checkout(String cardNumber, String nameOnCard, String securityDigit, LocalDate expirationDate, double amount) {
        Optional<Mastercard> optionalCard = this.getByCardNumber(cardNumber);
        if(optionalCard.isPresent() && optionalCard.get().isDeleted() == false){
            Mastercard card = optionalCard.get();

            if(card.getSecurityDigit().equals(securityDigit)){

                if(Comparator.compareExpirationDate(card.getExpirationDate() , expirationDate)){

                    if(card.getCurrentAmount() >= amount){

                        return this.pay(card , amount);

                    }

                }

            }

        }

        return false;
    }

    @Override
    public boolean pay(Mastercard mastercard, double amount) {
        mastercard.pay(amount);
        mastercard = this.mastercardRepository.save(mastercard);

        MastercardTransaction mastercardTransaction = new MastercardTransaction(amount, mastercard);
        this.mastercardRepository.save(mastercard);

        return true;
    }

    @Override
    public Mastercard saveCard(Mastercard card) {
        return this.mastercardRepository.save(card);
    }
}
