package com.top.shop.user.api.server;

import com.top.shop.user.domain.PaymentInformation;
import com.top.shop.user.query.service.PaymentInfromationQuery;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentInformationApi {
    @Autowired
    PaymentInfromationQuery paymentInfromationQuery;

    @GetMapping("/paymentInformation/vendor/{id}")
    @Operation(summary = "Payment information" , description="Reterive Payment information")
    public List<PaymentInformation> getPaymentInformation_vend(@PathVariable Long id){
       return paymentInfromationQuery.findVendorPInfoById(id);
    }
    @GetMapping("/paymentInformation/reg_user/{id}")
    @Operation(summary = "Payment information" , description="Reterive Payment information")
    public List<PaymentInformation> getPaymentInformation_Reg(@PathVariable Long id){
        return paymentInfromationQuery.findRegUserPInfoById(id);
    }
}
