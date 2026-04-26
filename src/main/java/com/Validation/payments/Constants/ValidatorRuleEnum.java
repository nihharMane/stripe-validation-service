package com.Validation.payments.Constants;

import com.Validation.payments.service.BusinessValidator;
import com.Validation.payments.Validator.DuplicateTxnValidation;

import java.util.Optional;

public enum ValidatorRuleEnum {

    VALIDATOR_RULE1("Duplicate_Txn_Validator", DuplicateTxnValidation.class);

    private final String ruleName;
    private final Class<? extends BusinessValidator> validatorClass;

    ValidatorRuleEnum(String ruleName, Class<? extends BusinessValidator> validatorClass) {
        this.ruleName = ruleName;
        this.validatorClass = validatorClass;
    }

    public static Optional<Class<? extends BusinessValidator>> getValidatorClassByRule(String ruleName) {
        if(ruleName == null){
            return Optional.empty();
        }

        for (ValidatorRuleEnum rule : values()) {
            if (rule.ruleName.equals(ruleName)) {
                return Optional.of(rule.validatorClass);
            }
        }
        return Optional.empty();
    }
}

