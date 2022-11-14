package com.empresax.core.application.rest.exception.handler;

import java.util.Date;
import java.util.MissingFormatArgumentException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.empresax.core.domain.model.dto.ErrorMessage;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

/**
 * Controllador de excepciones global
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
     * Se debe a que la entrada no se asigna a la definicion de destino ex: in: int
     * post:str
     */
    @ExceptionHandler(value = { MismatchedInputException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage globalExceptionHandler(MismatchedInputException e, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST,
                new Date(),
                e.getMessage(),
                request.getDescription(false));
    }

    /*
     * Se lanza cuando hay un especificador de formato que no tiene un argumento
     * correspondiente
     * o si su indice de argumento hace referencia a un argumento que no existe
     * ex: RoleType.Hora
     */
    @ExceptionHandler(value = { MissingFormatArgumentException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage globalMissingErrorHandler(MissingFormatArgumentException e, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST,
                new Date(),
                e.getMessage(),
                request.getDescription(false));
    }

    /*
     * La exepcion se genera cuando no se cumple con las validaciones establecidad
     * por el javax.validation.constraints
     */
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage globalValidExveption(MethodArgumentNotValidException e, WebRequest request) {
        StringBuilder msg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> msg.append(error.getDefaultMessage()).append(", "));
        int len = msg.length();
        msg.delete(len - 2, len);

        System.out.println();
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST,
                new Date(),
                msg.toString(),
                request.getDescription(false));
    }

}