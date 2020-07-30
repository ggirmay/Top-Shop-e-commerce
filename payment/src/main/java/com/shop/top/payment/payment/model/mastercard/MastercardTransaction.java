package com.shop.top.payment.payment.model.mastercard;

import com.shop.top.payment.payment.utils.Generator;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Entity(name = "mastercard_transaction")
public class MastercardTransaction {

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
    private Mastercard cardNumber;

    public MastercardTransaction() {
    }

    public MastercardTransaction(double amountOfPurchase, Mastercard cardNumber) {
        this.amountOfPurchase = amountOfPurchase;
        this.cardNumber = cardNumber;

        this.transactionID = Generator.generateTransactionID("V");
        this.dateOfPurchase = LocalDate.now();
        this.remainingAmount = cardNumber.getCurrentAmount() - this.amountOfPurchase;
    }

    public MastercardTransaction(String transactionID, LocalDate dateOfPurchase, double amountOfPurchase,
                           double remainingAmount, Mastercard cardNumber) {
        this.transactionID = transactionID;
        this.dateOfPurchase = dateOfPurchase;
        this.amountOfPurchase = amountOfPurchase;
        this.remainingAmount = remainingAmount;
        this.cardNumber = cardNumber;
    }
}
