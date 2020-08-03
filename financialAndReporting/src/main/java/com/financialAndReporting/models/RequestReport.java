package com.financialAndReporting.models;


import javax.persistence.*;
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
//    @OneToOne
//    private GeneratedReport generatedReport;
    public RequestReport() {

    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
