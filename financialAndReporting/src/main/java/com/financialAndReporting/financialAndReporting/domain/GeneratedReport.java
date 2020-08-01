package com.financialAndReporting.financialAndReporting.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GeneratedReport {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Order order;
    private Product product;
}
