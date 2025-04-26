package com.effective.project.bank.card.management.system.exception.type;

import com.effective.project.bank.card.management.system.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CardAlreadyBlockedException extends AbstractException {

    public CardAlreadyBlockedException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}