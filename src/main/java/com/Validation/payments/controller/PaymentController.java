package com.Validation.payments.controller;

import com.Validation.payments.pojo.PaymentRequest;
import com.Validation.payments.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
@Slf4j
@RequiredArgsConstructor

public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping
    public String createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        log.info("Creating Payment..." );
        String serviceResponse= paymentService.ValidateAndCreatePayment(paymentRequest);
        log.info(" Payment created successfully {}",serviceResponse );
        return serviceResponse;

    }
}
