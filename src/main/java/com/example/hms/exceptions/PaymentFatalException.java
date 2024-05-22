package com.example.hms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class PaymentFatalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PaymentFatalException() {
        super();
    }

    public PaymentFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentFatalException(String message) {
        super(message);
    }

    public PaymentFatalException(Throwable cause) {
        super(cause);
    }
}
