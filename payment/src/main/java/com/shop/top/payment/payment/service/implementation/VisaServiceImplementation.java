package com.shop.top.payment.payment.service.implementation;

import com.shop.top.payment.payment.exception.CardCreationException;
import com.shop.top.payment.payment.model.CreditCard;
import com.shop.top.payment.payment.exception.CardNotFoundException;
import com.shop.top.payment.payment.model.visa.Visa;
import com.shop.top.payment.payment.model.visa.VisaTransaction;
import com.shop.top.payment.payment.repository.VisaRepository;
import com.shop.top.payment.payment.repository.VisaTransactionRepository;
import com.shop.top.payment.payment.service.VisaService;
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
public class VisaServiceImplementation implements VisaService {

    @Autowired
    private VisaRepository visaRepository;

    @Autowired
    private VisaTransactionRepository visaTransactionRepository;

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
        return saveCard(card);
    }

    @Override
    public Visa saveCard(Visa card) {
        return this.visaRepository.save(card);
    }

    public Visa createCard(String lastName , String firstName , String nameOnCard) throws CardCreationException {

        if (nameOnCard.equalsIgnoreCase("")){
            throw new CardCreationException("Name on card cannot be Empty");
        }

        if (lastName.equalsIgnoreCase("") && firstName.equalsIgnoreCase(""))
            throw new CardCreationException("A card must contains at least a Lastname or a Firstname");

        String cardNumber = Generator.generateCreditCardNumber(CreditCard.VISA.value());
        Optional<Visa> card = this.getByCardNumber(cardNumber);

        while(card.isPresent()){
            cardNumber = Generator.generateCreditCardNumber(CreditCard.VISA.value());
            card = this.getByCardNumber(cardNumber);
        }

        return new Visa(lastName, firstName, nameOnCard , cardNumber);
    }

    @Override
    public /*VisaTransaction*/ boolean checkout(String cardNumber, String nameOnCard, String securityDigit, LocalDate expirationDate, double amount) {

        Optional<Visa> optionalCard = this.getByCardNumber(cardNumber);
        if(optionalCard.isPresent() && optionalCard.get().isDeleted() == false){
            Visa card = optionalCard.get();

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
    public boolean pay(Visa card, double amount) {

        System.out.println(card);

        card.pay(amount);
        card = this.visaRepository.save(card);

        VisaTransaction visaTransaction = new VisaTransaction(amount, card);
        this.visaTransactionRepository.save(visaTransaction);

        return true;
    }


}
