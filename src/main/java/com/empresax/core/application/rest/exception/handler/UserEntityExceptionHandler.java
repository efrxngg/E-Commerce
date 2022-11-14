package com.empresax.core.application.rest.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.empresax.core.domain.exception.UserEntityNotFoundException;
import com.empresax.core.domain.model.dto.ErrorMessage;

@RestControllerAdvice
public class UserEntityExceptionHandler {

    @ExceptionHandler(value = { UserEntityNotFoundException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage userEntityNotFoundException(UserEntityNotFoundException e, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST,
                new Date(),
                e.getMessage(),
                request.getDescription(false));
    }

}