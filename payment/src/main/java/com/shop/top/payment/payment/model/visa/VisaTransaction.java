package com.shop.top.payment.payment.model.visa;

import com.shop.top.payment.payment.model.CreditCard;
import com.shop.top.payment.payment.utils.Generator;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Entity(name = "visa_transaction")
public class VisaTransaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String transactionID;

    //@Temporal(TemporalType.DATE)
    @NotNull
    @Column(nullable = false)
    private LocalDate dateOfPurchase;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private double amountOfPurchase;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private double remainingAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    private Visa cardNumber;

    public VisaTransaction() {
    }

    public VisaTransaction(double amountOfPurchase, Visa cardNumber) {
        this.amountOfPurchase = amountOfPurchase;
        this.cardNumber = cardNumber;

        this.transactionID = Generator.generateTransactionID(CreditCard.VISA.initial());
        this.dateOfPurchase = LocalDate.now();
        this.remainingAmount = cardNumber.getCurrentAmount(); // - this.amountOfPurchase
    }

    public VisaTransaction(String transactionID, LocalDate dateOfPurchase, double amountOfPurchase,
                           double remainingAmount, Visa cardNumber) {
        this.transactionID = transactionID;
        this.dateOfPurchase = dateOfPurchase;
        this.amountOfPurchase = amountOfPurchase;
        this.remainingAmount = remainingAmount;
        this.cardNumber = cardNumber;
    }
}
