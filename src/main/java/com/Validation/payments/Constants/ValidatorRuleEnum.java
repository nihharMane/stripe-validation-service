package com.Validation.payments.Constants;

import com.Validation.payments.service.BusinessValidator;
import com.Validation.payments.serviceImpl.Validator.ValidatorRule1;
import com.Validation.payments.serviceImpl.Validator.ValidatorRule2;
import com.Validation.payments.serviceImpl.Validator.ValidatorRule3;

import java.util.Optional;

public enum ValidatorRuleEnum {
    VALIDATOR_RULE1("VALIDATOR_RULE1", ValidatorRule1.class),
    VALIDATOR_RULE2("VALIDATOR_RULE2", ValidatorRule2.class),
    VALIDATOR_RULE3("VALIDATOR_RULE3", ValidatorRule3.class);

    private final String ruleName;
    private final Class<? extends BusinessValidator> validatorClass;

    ValidatorRuleEnum(String ruleName, Class<? extends BusinessValidator> validatorClass) {
        this.ruleName = ruleName;
        this.validatorClass = validatorClass;
    }

    public String getRuleName() {
        return ruleName;
    }

    public Class<? extends BusinessValidator> getValidatorClass() {
        return validatorClass;
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
