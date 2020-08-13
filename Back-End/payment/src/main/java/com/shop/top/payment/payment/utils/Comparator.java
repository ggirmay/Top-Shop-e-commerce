package com.shop.top.payment.payment.utils;

import java.time.LocalDate;

public class Comparator {

    public static boolean compareExpirationDate(LocalDate original , LocalDate data){

        if(original.getMonthValue() != data.getMonthValue()) return false;

        if(original.getYear() != data.getYear()) return false;

//        LocalDate today = LocalDate.now();
//        if(original.getMonthValue() < today.getMonthValue()){
//            if(original.getYear() <= today.getYear()){
//                return false;
//            }
//        }
//
//        if(original.getYear() < today.getYear()) return false;

        return true;

    }

}
