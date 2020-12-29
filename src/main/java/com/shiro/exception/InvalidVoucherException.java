package com.shiro.exception;

public class InvalidVoucherException extends RuntimeException{

    public InvalidVoucherException() {
    }

    public InvalidVoucherException(String message) {
        super(message);
    }

    public InvalidVoucherException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidVoucherException(Throwable cause) {
        super(cause);
    }

    public InvalidVoucherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
