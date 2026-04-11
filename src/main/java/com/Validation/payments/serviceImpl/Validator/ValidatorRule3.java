package com.Validation.payments.serviceImpl.Validator;

import com.Validation.payments.pojo.PaymentRequest;
import com.Validation.payments.service.BusinessValidator;
import org.springframework.stereotype.Service;

@Service
public class ValidatorRule3 implements BusinessValidator {
    @Override
    public void validate(PaymentRequest paymentRequest) {
       long amount = paymentRequest.getPayment().getAmount();
       if(amount >500){
           throw new RuntimeException("Invalid amount request");
        }
    }
}
