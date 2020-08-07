package com.top.shop.user.query.service;

import com.top.shop.user.domain.PaymentInformation;
import com.top.shop.user.query.action.PaymentInfoQueryAction;
import com.top.shop.user.query.action.VendorQueryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentInfromationQuery {

    @Autowired
    PaymentInfoQueryAction paymentInfoQueryAction;
    @Autowired
    VendorQueryAction vendorQueryAction;
    @Autowired
    RegisterduserQueryService registerduserQueryService;


    public List<PaymentInformation> findVendorPInfoById(Long id) {
       return vendorQueryAction.findPaymentInfo(id);
    }

    public List<PaymentInformation> findRegUserPInfoById(Long id) {
        return  registerduserQueryService.findAllPaymetnInformation(id);
    }
}
