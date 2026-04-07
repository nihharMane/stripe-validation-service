package com.Validation.payments.Constants;

public enum ErrorCode {


    SUCCESS_URL_BLANK(10001, "Success URL must not be empty"),
    SUCCESS_URL_INVALID(10002, "Success URL must be a valid URL"),


    CANCEL_URL_BLANK(10003, "Cancel URL must not be empty"),
    CANCEL_URL_INVALID(10004, "Cancel URL must be a valid URL"),


    LINE_ITEMS_EMPTY(10005, "Line items must not be empty"),


    PRODUCT_NAME_BLANK(10006, "Product name must not be empty"),


    CURRENCY_BLANK(10007, "Currency must not be empty"),
    CURRENCY_INVALID(10008, "Currency must be a valid 3-letter ISO code"),


    UNIT_AMOUNT_INVALID(10009, "Unit amount must be greater than 0"),


    QUANTITY_INVALID(10010, "Quantity must be at least 1"),


    STRIPE_ERROR(20001, "Stripe error"),


    INTERNAL_ERROR(90001, "Internal server error");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}