package com.meilleurtaux.test.exception;

public class CommuneNotFoundException extends RuntimeException {
    public CommuneNotFoundException(String message) {
        super(message);
    }
}
