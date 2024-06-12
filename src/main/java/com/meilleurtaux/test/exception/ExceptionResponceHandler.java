package com.meilleurtaux.test.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestControllerAdvice
public class ExceptionResponceHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(CommuneNotFoundException.class)
    public ResponseEntity<String> handleCommuneNotFoundException(CommuneNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

	@ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<String> handleExternalServiceException(ExternalServiceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }


	@ExceptionHandler(CodePostalInvalidException.class)
    public ResponseEntity<String> handleCodePostalInvalidException(CodePostalInvalidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
