package com.mamamoney.challenge.exceptions;

public class InvalidUserRequestException extends Exception {
    private String message;

    public InvalidUserRequestException() {
        super();
    }

    public InvalidUserRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
