package com.mikolaj.promocodes.api.exceptions;

public class PromoCodeAlreadyExistsException extends RuntimeException{
    public PromoCodeAlreadyExistsException(String message) {
        super(message);
    }
}
