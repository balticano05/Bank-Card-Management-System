package com.effective.project.bank.card.management.system.exception;

import com.effective.project.bank.card.management.system.exception.response.ExceptionResponse;
import com.effective.project.bank.card.management.system.exception.type.AmountMustBePositiveException;
import com.effective.project.bank.card.management.system.exception.type.CardAlreadyBlockedException;
import com.effective.project.bank.card.management.system.exception.type.InsufficientFundsException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionResponse handleNotFound(EntityNotFoundException ex) {
        return ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ExceptionResponse handleInsufficientFunds(InsufficientFundsException ex) {
        return ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(CardAlreadyBlockedException.class)
    public ExceptionResponse handleCardAlreadyBlocked(CardAlreadyBlockedException ex) {
        return ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(AmountMustBePositiveException.class)
    public ExceptionResponse handleCardAlreadyBlocked(AmountMustBePositiveException ex) {
        return ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

}