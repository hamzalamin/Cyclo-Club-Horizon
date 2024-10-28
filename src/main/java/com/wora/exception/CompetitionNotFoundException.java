package com.wora.exception;

public class CompetitionNotFoundException extends RuntimeException{

    public CompetitionNotFoundException(String message) {
        super(message);
    }
}
