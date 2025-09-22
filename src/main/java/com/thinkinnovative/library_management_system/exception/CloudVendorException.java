package com.thinkinnovative.library_management_system.exception;

import org.springframework.http.HttpStatus;

public class CloudVendorException {

    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpstatus;

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpstatus() {
        return httpstatus;
    }

    public CloudVendorException(String message, Throwable throwable, HttpStatus httpstatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpstatus = httpstatus;
    }
}
