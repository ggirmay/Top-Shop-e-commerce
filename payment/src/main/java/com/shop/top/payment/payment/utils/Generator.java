package com.shop.top.payment.payment.utils;

import com.shop.top.payment.payment.utils.Generator;

import java.util.Calendar;

public class Generator {

    private static Calendar date;
    private static int counter;

    static {
        date = Calendar.getInstance();
        date.set(Calendar.HOUR , 0);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        counter = 0;
    }

    public static String generateCreditCardNumber(int startingDigit) {

        String cardNumber = "" + startingDigit;
        int randomNumber = 0;
        for(int i = 0 ; i < 15 ; i++){
            randomNumber = (int) Math.floor(Math.random() * 10);
            cardNumber += randomNumber;
        }

        int sum = 0;
        int digit = 0;

        for(int i = 0 ; i < 16 ; i += 2){
            sum += Integer.valueOf(cardNumber.charAt(i));
        }

        for(int i = 1 ; i < 16 ; i += 2){
            digit = 2 * Integer.valueOf(cardNumber.charAt(i));
            if(digit > 9) digit = (digit % 10) + (digit / 10);
            sum += digit;
        }

        int remaining = sum % 10;
        if(remaining != 0) {
            String lastDigit = String.valueOf(cardNumber.charAt(15));
            if(remaining <= Integer.valueOf(lastDigit))
                cardNumber = cardNumber.substring(0 , 15) + (Integer.valueOf(lastDigit) - remaining);

            else
                cardNumber = cardNumber.substring(0 , 15) + (Integer.valueOf(lastDigit) + 10 - remaining);

        }

        return cardNumber;
    }

    public static String generateSecurityDigit(){
        String digit = "";
        for(int i = 0 ; i < 3 ; i++){
            digit += (int) Math.floor(Math.random() * 10);
        }

        return digit;
    }

    public static String generateRandomDigit(int digitNumber){
        String digit = "";
        for(int i = 0 ; i < digitNumber ; i++){
            digit += (int) Math.floor(Math.random() * 10);
        }

        return digit;
    }

    public static String generateTransactionID(String type){
        Calendar newDate = Calendar.getInstance();
        newDate.setLenient(false);
        newDate.set(Calendar.HOUR , 0);
        newDate.set(Calendar.HOUR_OF_DAY, 0);
        newDate.set(Calendar.MINUTE, 0);
        newDate.set(Calendar.SECOND, 0);
        newDate.set(Calendar.MILLISECOND, 0);

        if(date.before(newDate)){
            counter = 0;
            date = newDate;
        }

        String id = type + "-" + date.get(Calendar.MONTH) + date.get(Calendar.DAY_OF_MONTH) + date.get(Calendar.YEAR) +
                "-" + date.getTimeInMillis() + generateRandomDigit(6);
        counter ++;

        return id;
    }


}
