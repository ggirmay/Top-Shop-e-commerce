package com.financialAndReporting.financialAndReporting.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity

public class RequestReport {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String reportType;
    private LocalDate dateRequested;
    private String reportStatus;
    private String dateRangeCovered;
    public RequestReport() {

    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public LocalDate getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(LocalDate dateRequested) {
        this.dateRequested = dateRequested;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getDateRangeCovered() {
        return dateRangeCovered;
    }

    public void setDateRangeCovered(String dateRangeCovered) {
        this.dateRangeCovered = dateRangeCovered;
    }
}
