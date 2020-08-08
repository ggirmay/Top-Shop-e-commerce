package com.shop.top.payment.payment.controller;

import com.shop.top.payment.payment.exception.CardCreationException;
import com.shop.top.payment.payment.exception.CardNotFoundException;
import com.shop.top.payment.payment.model.mastercard.Mastercard;
import com.shop.top.payment.payment.model.mastercard.MastercardTransaction;
import com.shop.top.payment.payment.service.MastercardService;
import com.shop.top.payment.payment.utils.Getters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value = "/mastercard-api")

public class MastercardController {

    @Autowired
    private MastercardService mastercardService;
       @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = {"/" , "" , "/list"})
    public ResponseEntity<List<Mastercard>> getAllMastercard(){

        return ResponseEntity.ok().body(this.mastercardService.getAll());
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = {"/find", "/list/find"})
    public ResponseEntity<?> getOneMastercardByCardNumber(@RequestParam(value = "cardNumber") String cardNumber){
        Optional<Mastercard> mastercard = this.mastercardService.getByCardNumber(cardNumber);
        if(mastercard.isPresent())
            return ResponseEntity.ok().body(mastercard.get());

        return ResponseEntity.badRequest().body("Card with number " + cardNumber + " not found");
    }

    /*
    * Getters is in the utils package
    *
    * */
    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping(value = "/save")
    public ResponseEntity<?> uploadNewCard(@RequestBody Map<String , ?> data) {

        String lastName = Getters.extractString(data.get("lastName"));
        String firstName = Getters.extractString(data.get("firstName"));
        String nameOnCard = Getters.extractString(data.get("nameOnCard"));

        Mastercard mastercard = null;

        try {
            mastercard = this.mastercardService.createCard(lastName , firstName , nameOnCard);
        } catch (CardCreationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(this.mastercardService.saveCard(mastercard));
    }

    @Secured(value = {"ROLE_ADMIN"})
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteCard(@RequestParam("cardNumber") String cardNumber){

        boolean result = false;

        try {
            result = this.mastercardService.deleteCard(cardNumber);
        } catch (CardNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(result);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PutMapping(value = "/edit")
    public ResponseEntity<?> updateCard(@Valid @RequestBody Mastercard mastercard , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        return ResponseEntity.ok().body(this.mastercardService.updateCard(mastercard));
    }


}
