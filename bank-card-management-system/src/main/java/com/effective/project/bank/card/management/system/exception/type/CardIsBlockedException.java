package com.effective.project.bank.card.management.system.exception.type;

import com.effective.project.bank.card.management.system.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CardIsBlockedException extends AbstractException {

    public CardIsBlockedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }

}