package com.Validation.payments.controller;

import com.Validation.payments.pojo.CreatePaymentRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
@Slf4j
public class PaymentController {

    @PostMapping
    public String createPayment(@Valid @RequestBody CreatePaymentRequest paymentRequest) {
        log.info("Creating Payment..." );
        return "Payment created successfully!" + paymentRequest;
    }
}
