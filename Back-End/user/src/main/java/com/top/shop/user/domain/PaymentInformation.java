package com.top.shop.user.domain;

import com.sun.istack.NotNull;
import com.top.shop.user.util.CardType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 * @author Yome Mengistu
 */
@Entity
@Data
public class PaymentInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String nameOnCard;
    @NotNull
    @Size(min = 3,max = 3)
    private String secCode;
    @NotNull
    private String expDate;
    @NotNull
    private CardType cardType;
    @NotNull
    @Size(min = 16,max = 16)
    private String cardNumber;

    }
