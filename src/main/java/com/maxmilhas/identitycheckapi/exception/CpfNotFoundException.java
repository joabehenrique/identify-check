package com.maxmilhas.identitycheckapi.exception;

public class CpfNotFoundException extends RuntimeException{
    public CpfNotFoundException(String message) {
        super(message);
    }
}
