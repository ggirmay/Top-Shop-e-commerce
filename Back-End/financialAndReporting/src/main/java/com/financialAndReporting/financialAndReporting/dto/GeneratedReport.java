package com.financialAndReporting.financialAndReporting.dto;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class GeneratedReport {
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
    private Long id;
    private Order order;
    private Product product;
}
