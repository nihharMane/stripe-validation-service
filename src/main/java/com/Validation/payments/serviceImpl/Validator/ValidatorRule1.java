package com.Validation.payments.serviceImpl.Validator;

import com.Validation.payments.Constants.ErrorCode;
import com.Validation.payments.Exception.PaymentValidationException;
import com.Validation.payments.pojo.PaymentRequest;
import com.Validation.payments.service.BusinessValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ValidatorRule1 implements BusinessValidator {
    @Override
    public void validate(PaymentRequest paymentRequest) {
       String name=paymentRequest.getUser().getFirstname();
       if(name.contains("hello")){
           throw new PaymentValidationException(
                   ErrorCode.FIRSTNAME_CONTAIN_HELLO.getCode(),
                   ErrorCode.FIRSTNAME_CONTAIN_HELLO.getMessage(),
                   HttpStatus.BAD_REQUEST
           );
        }
    }
}
