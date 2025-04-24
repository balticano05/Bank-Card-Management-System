package com.effective.project.bank.card.management.system.security.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponse {

    private String token;

}