package com.Validation.payments.Exception;

import com.Validation.payments.Constants.ErrorCode;
import com.Validation.payments.pojo.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidationException(
            MethodArgumentNotValidException ex) {

        List<ErrorResponse> errorList = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {

            String key = error.getDefaultMessage(); // "CANCEL_URL_INVALID"
            ErrorCode errorCode;

            try {
                errorCode = ErrorCode.valueOf(key);
            } catch (IllegalArgumentException e) {
                errorCode = ErrorCode.INTERNAL_ERROR;
            }

            ErrorResponse response = ErrorResponse.builder()
                    .errorCode(errorCode.getCode())
                    .errorMessage(errorCode.getMessage())
                    .build();

            errorList.add(response);
        });

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }
}