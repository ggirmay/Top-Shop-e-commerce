package com.shop.top.payment.payment.utils;

import java.time.LocalDate;

public class Getters {

    public static String extractString( Object object ){

        try {
            return String.valueOf(object);
        }catch (NullPointerException e){
            return "";
        }

    }

    public static LocalDate extractDate( Object object ){
        LocalDate date = (LocalDate) object;
        return date;
    }


}
