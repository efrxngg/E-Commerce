package com.empresax.core.domain.exception;

public class UserEntityNotFoundException extends RuntimeException {

    public UserEntityNotFoundException(String msg) {
        super(msg);
    }

}