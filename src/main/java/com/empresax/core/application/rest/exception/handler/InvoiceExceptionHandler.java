package com.empresax.core.application.rest.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.empresax.core.domain.exception.InvoiceException;
import com.empresax.core.domain.model.dto.ErrorMessage;

@RestControllerAdvice
public class InvoiceExceptionHandler {

    @ExceptionHandler(value = { InvoiceException.class })
    public ResponseEntity<ErrorMessage> globalInvoiceException(InvoiceException e, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorMessage(
                        HttpStatus.BAD_REQUEST,
                        new Date(),
                        e.getMessage(),
                        request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }
}