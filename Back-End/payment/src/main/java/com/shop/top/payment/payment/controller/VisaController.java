package com.shop.top.payment.payment.controller;

import com.shop.top.payment.payment.exception.CardCreationException;
import com.shop.top.payment.payment.exception.CardNotFoundException;
import com.shop.top.payment.payment.model.visa.Visa;
import com.shop.top.payment.payment.model.visa.VisaTransaction;
import com.shop.top.payment.payment.service.VisaService;
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
@RequestMapping(value = "/visa-api")
public class VisaController {

    @Autowired
    private VisaService visaService;

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = {"/" , "" , "/list"})
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Visa>> getAllVisa(){

        return ResponseEntity.ok().body(this.visaService.getAll());
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = {"/find", "/list/find"})
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getOneVisaByCardNumber(@RequestParam(value = "cardNumber") String cardNumber){
        Optional<Visa> visa = this.visaService.getByCardNumber(cardNumber);
        if(visa.isPresent())
            return ResponseEntity.ok().body(visa.get());

        return ResponseEntity.badRequest().body("Card with number " + cardNumber + " not found");
    }

    /*
     * Getters is in the utils package
     *
     * */
    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping(value = "/save")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> uploadNewCard(@RequestBody Map<String , ?> data) {

        String lastName = Getters.extractString(data.get("lastName"));
        String firstName = Getters.extractString(data.get("firstName"));
        String nameOnCard = Getters.extractString(data.get("nameOnCard"));

        Visa visa = null;

        try {
            visa = this.visaService.createCard(lastName , firstName , nameOnCard);
        } catch (CardCreationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(this.visaService.saveCard(visa));
    }

    @Secured(value = {"ROLE_ADMIN"})
    @DeleteMapping(value = "/delete")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteCard(@RequestParam("cardNumber") String cardNumber){

        boolean result = false;

        try {
            result = this.visaService.deleteCard(cardNumber);
        } catch (CardNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(result);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PutMapping(value = "/edit")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> updateCard(@Valid @RequestBody Visa visa , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        return ResponseEntity.ok().body(this.visaService.updateCard(visa));

    }

}
