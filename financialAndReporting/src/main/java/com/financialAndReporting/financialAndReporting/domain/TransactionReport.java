package com.financialAndReporting.financialAndReporting.domain;

import com.google.inject.internal.cglib.core.$Local;

import java.time.LocalDate;

public class TransactionReport {
private LocalDate date;
private String transactionType;
private double totalProductCharges;
private double topShopFee;
private double total;

    public TransactionReport(LocalDate date, String transactionType, double totalProductCharges, double topShopFee, double total) {
        this.date = date;
        this.transactionType = transactionType;
        this.totalProductCharges = totalProductCharges;
        this.topShopFee = topShopFee;
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTotalProductCharges() {
        return totalProductCharges;
    }

    public void setTotalProductCharges(double totalProductCharges) {
        this.totalProductCharges = totalProductCharges;
    }

    public double getTopShopFee() {
        return topShopFee;
    }

    public void setTopShopFee(double topShopFee) {
        this.topShopFee = topShopFee;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
