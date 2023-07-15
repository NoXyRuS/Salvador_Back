package com.example.stripe_libreriasalvador.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}

