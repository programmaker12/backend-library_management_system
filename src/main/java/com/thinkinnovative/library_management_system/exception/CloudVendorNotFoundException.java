package com.thinkinnovative.library_management_system.exception;

public class CloudVendorNotFoundException extends RuntimeException
{

    public CloudVendorNotFoundException(String message) {
        super(message);
    }

    public CloudVendorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
