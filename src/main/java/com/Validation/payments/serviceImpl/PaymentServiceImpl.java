package com.Validation.payments.serviceImpl;

import com.Validation.payments.Constants.ValidatorRuleEnum;
import com.Validation.payments.pojo.PaymentRequest;
import com.Validation.payments.service.BusinessValidator;
import com.Validation.payments.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    //read value form application.properties file of validator
    @Value("${validator.rule.name}")
    private String validatorRuleName;


    private final ApplicationContext applicationContext;

    @Override
    public String ValidateAndCreatePayment(PaymentRequest paymentRequest) {
        String[] rules= validatorRuleName.split(",");
        for (String rule : rules){
            log.info("Applying Validator rule:{}", rule);


         Optional<Class<? extends BusinessValidator>> validatorclass =   ValidatorRuleEnum.getValidatorClassByRule(rule.trim());
         if(!validatorclass.isPresent()){
             log.info("Validator class not found:{}",rule);
             continue;
         }

         BusinessValidator businessValidator = applicationContext.getBean(validatorclass.get());
         if(businessValidator == null){
            log.warn("BusinessValidator not found:{}",validatorclass.get().getName());
            continue;
        }
         businessValidator.validate(paymentRequest);
            log.info("Validator rule {} applied successfully : {}",paymentRequest);

        }
        log.info("Validating Payment..." );
        return "Payment validated and created successfully!";
    }

}
