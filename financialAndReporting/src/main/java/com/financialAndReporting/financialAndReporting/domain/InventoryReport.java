package com.financialAndReporting.financialAndReporting.domain;

import java.time.LocalDate;

public class InventoryReport {
    private LocalDate from;
    private LocalDate to;
    private int inStock;
    private int purchesedProduct;

    public InventoryReport(LocalDate from, LocalDate to, int inStock, int purchesedProduct) {
        this.from = from;
        this.to = to;
        this.inStock = inStock;
        this.purchesedProduct = purchesedProduct;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getPurchesedProduct() {
        return purchesedProduct;
    }

    public void setPurchesedProduct(int purchesedProduct) {
        this.purchesedProduct = purchesedProduct;
    }
}
