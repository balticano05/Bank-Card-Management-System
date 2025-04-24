package com.effective.project.bank.card.management.system.exception.type;

import com.effective.project.bank.card.management.system.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CustomServletException extends AbstractException {

    public CustomServletException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
