package com.thinkinnovative.library_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class CloudVendorExceptionHandler {

    @ExceptionHandler(value={CloudVendorNotFoundException.class})
    public ResponseEntity<Object> handleCloudVendorNotFoundException (
            CloudVendorNotFoundException cloudVendorNotFoundException
    ) {
        CloudVendorException vendorException = new CloudVendorException(cloudVendorNotFoundException.getMessage(), cloudVendorNotFoundException.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(vendorException, HttpStatus.NOT_FOUND );
    }
}
