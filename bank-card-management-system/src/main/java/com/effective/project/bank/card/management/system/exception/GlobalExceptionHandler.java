package com.effective.project.bank.card.management.system.exception;

import com.effective.project.bank.card.management.system.exception.response.ExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(AbstractException.class)
    public ExceptionResponse handleAbstract(AbstractException ex) {
        return ExceptionResponse.builder()
                .status(ex.getHttpStatus())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

}