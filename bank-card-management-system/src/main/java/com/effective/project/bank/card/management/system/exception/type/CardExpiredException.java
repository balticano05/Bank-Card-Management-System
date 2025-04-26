package com.effective.project.bank.card.management.system.exception.type;

import com.effective.project.bank.card.management.system.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CardExpiredException extends AbstractException {

    public CardExpiredException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }

}
