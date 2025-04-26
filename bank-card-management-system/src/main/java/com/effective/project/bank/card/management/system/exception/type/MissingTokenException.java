package com.effective.project.bank.card.management.system.exception.type;

import com.effective.project.bank.card.management.system.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class MissingTokenException extends AbstractException {

    public MissingTokenException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
