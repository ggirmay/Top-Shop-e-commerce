package com.top.shop.user.query.action;

import com.top.shop.user.domain.PaymentInformation;
import com.top.shop.user.repository.PaymentInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentInfoQueryAction {
    @Autowired
    PaymentInformationRepository paymentInformationRepository;

}
