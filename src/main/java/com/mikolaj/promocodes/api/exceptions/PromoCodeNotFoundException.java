package com.mikolaj.promocodes.api.exceptions;

public class PromoCodeNotFoundException extends RuntimeException {
    public PromoCodeNotFoundException(String message) {
        super(message);
    }
}
