package com.example.stripe_libreriasalvador.exception;


public class ValidationException extends  RuntimeException{

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
