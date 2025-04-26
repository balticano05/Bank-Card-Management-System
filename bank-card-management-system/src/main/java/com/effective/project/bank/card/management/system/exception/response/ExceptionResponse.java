package com.effective.project.bank.card.management.system.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class ExceptionResponse {

    private final HttpStatus status;

    private final String message;

    private final Instant timestamp = Instant.now();

}