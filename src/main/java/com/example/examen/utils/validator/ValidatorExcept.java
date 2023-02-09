package com.example.examen.utils.validator;

public class ValidatorExcept extends RuntimeException{
    public ValidatorExcept() {
        super();
    }

    public ValidatorExcept(String message) {
        super(message);
    }

    public ValidatorExcept(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorExcept(Throwable cause) {
        super(cause);
    }

    protected ValidatorExcept(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
