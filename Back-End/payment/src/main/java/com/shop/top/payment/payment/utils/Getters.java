package com.shop.top.payment.payment.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Getters {

    public static String extractString( Object object ){

        try {
            return String.valueOf(object);
        }catch (NullPointerException e){
            return "";
        }

    }

    public static LocalDate extractDate( String date ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date , formatter);
    }


}
