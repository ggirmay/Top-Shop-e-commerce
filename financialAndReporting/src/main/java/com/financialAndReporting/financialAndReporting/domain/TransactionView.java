package com.financialAndReporting.financialAndReporting.domain;

import java.time.LocalDate;

public class TransactionView {
    private LocalDate statementPeriodFrom;
    private LocalDate statementPeriodTo;

    public TransactionView(LocalDate statementPeriodFrom, LocalDate statementPeriodTo) {
        this.statementPeriodFrom = statementPeriodFrom;
        this.statementPeriodTo = statementPeriodTo;
    }

    public LocalDate getStatementPeriodFrom() {
        return statementPeriodFrom;
    }

    public void setStatementPeriodFrom(LocalDate statementPeriodFrom) {
        this.statementPeriodFrom = statementPeriodFrom;
    }

    public LocalDate getStatementPeriodTo() {
        return statementPeriodTo;
    }

    public void setStatementPeriodTo(LocalDate statementPeriodTo) {
        this.statementPeriodTo = statementPeriodTo;
    }
}
