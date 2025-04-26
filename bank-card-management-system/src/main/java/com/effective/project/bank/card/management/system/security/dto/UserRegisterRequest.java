package com.effective.project.bank.card.management.system.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    private String email;

    private String password;

    private String fullName;

}