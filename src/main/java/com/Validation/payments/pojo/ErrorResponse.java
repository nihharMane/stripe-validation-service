package com.Validation.payments.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private int errorCode;
    private String errorMessage;
}
