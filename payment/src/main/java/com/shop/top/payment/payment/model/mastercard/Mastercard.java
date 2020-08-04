package com.shop.top.payment.payment.model.mastercard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.top.payment.payment.model.CreditCard;
import com.shop.top.payment.payment.utils.Generator;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Getter
@Entity(name = "Mastercard_Card")
public class Mastercard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "[4-5][1-9]{15}")
    @Column(nullable = false , unique = true)
    private String cardNumber;

    @NotNull @NotBlank @NotEmpty
    @Column(nullable = false)
    private String lastName;

    @NotNull @NotBlank @NotEmpty
    @Column(nullable = false)
    private String firstName;

    @NotNull @NotBlank @NotEmpty
    @Column(nullable = false)
    private String nameOnCard;

    @Transient
    private String issuer;

    //@Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "mm/yyyy")
    @NotNull
    @Column(nullable = false)
    private LocalDate expirationDate;

    @Pattern(regexp = "[0,9]{3}")
    @NotNull @NotBlank @NotEmpty
    @Column(nullable = false)
    private String securityDigit;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private double value;

    @NotNull
    @Column(nullable = false)
    private boolean upgrade;

    //@Temporal(TemporalType.DATE)
    private LocalDate upgradeDate;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private int numberOfUpgrade;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private double currentValue;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private double currentAmount;

    @Column(nullable = false)
    private boolean deleted;

    @OneToMany(mappedBy = "cardNumber")
    private List<MastercardTransaction> transactionList;

    public Mastercard() {
    }

    public Mastercard(String lastName, String firstName, String nameOnCard) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameOnCard = nameOnCard;

        this.cardNumber = Generator.generateCreditCardNumber(CreditCard.MASTERCARD.value());
        this.issuer = CreditCard.MASTERCARD.issuer();
        LocalDate.of(Calendar.getInstance().get(Calendar.YEAR) + 5 ,
                Calendar.getInstance().get(Calendar.MONTH) ,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        this.securityDigit = Generator.generateSecurityDigit();
        this.value = 1750d;
        this.upgrade = false;
        this.upgradeDate = null;
        this.numberOfUpgrade = 0;
        this.currentValue = this.value;
        this.currentAmount = this.value;
        this.deleted = false;

    }

    public Mastercard(String lastName, String firstName, String nameOnCard , String cardNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameOnCard = nameOnCard;

        this.cardNumber = cardNumber;
        this.issuer = CreditCard.MASTERCARD.issuer();
        LocalDate.of(Calendar.getInstance().get(Calendar.YEAR) + 5 ,
                Calendar.getInstance().get(Calendar.MONTH) ,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        this.securityDigit = Generator.generateSecurityDigit();
        this.value = 1750d;
        this.upgrade = false;
        this.upgradeDate = null;
        this.numberOfUpgrade = 0;
        this.currentValue = this.value;
        this.currentAmount = this.value;
        this.deleted = false;

    }

    private Mastercard(String cardNumber, String lastName, String firstName, String nameOnCard,
                       String issuer, LocalDate expirationDate, String securityDigit, double value, boolean deleted,
                       boolean upgrade, LocalDate upgradeDate, int numberOfUpgrade, double currentValue, double currentAmount) {
        this.cardNumber = cardNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameOnCard = nameOnCard;
        this.issuer = issuer;
        this.expirationDate = expirationDate;
        this.securityDigit = securityDigit;
        this.value = value;
        this.upgrade = upgrade;
        this.upgradeDate = upgradeDate;
        this.numberOfUpgrade = numberOfUpgrade;
        this.currentAmount = currentAmount;
        this.currentValue = currentValue;
        this.deleted = deleted;
    }

    public String getCardNumber() { return this.cardNumber; }

    public double getCurrentAmount(){
        return this.currentAmount;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getSecurityDigit() {
        return securityDigit;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void pay(double amount) {
        this.currentAmount -= amount;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(){
        this.deleted = !this.deleted;
    }


}
