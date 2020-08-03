package com.shop.top.payment.payment;

import com.shop.top.payment.payment.utils.Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratorTest {

    @Test
    public void creditCardNumberGenerator(){

        Generator generator = new Generator();

        int start = 4;
        String number = generator.generateCreditCardNumber(start);
        System.out.println(number);

        Assertions.assertTrue(number.startsWith("" + start));
        Assertions.assertTrue(number.length() == 16);
        Assertions.assertTrue(number.matches("[" + start + "][0-9]{15}"));

    }

    @Test
    public void generateSecurityDigitTest(){
        String digit = Generator.generateSecurityDigit();
        System.out.println(digit);

        Assertions.assertTrue(digit.length() == 3);
        Assertions.assertTrue(digit.matches("[0-9]{3}"));

    }

    @Test
    public void generateTransactionIDTest(){
        String id = Generator.generateTransactionID("V");
        System.out.println(id);
        Assertions.assertTrue(id.startsWith("V"));
    }

}
