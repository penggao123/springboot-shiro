package com.shiro.exception;

public class HttpRequestMethodNotSupportedException extends RuntimeException{
    public HttpRequestMethodNotSupportedException() {
        super();
    }

    public HttpRequestMethodNotSupportedException(String message) {
        super(message);
    }

    public HttpRequestMethodNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpRequestMethodNotSupportedException(Throwable cause) {
        super(cause);
    }

    protected HttpRequestMethodNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
