package com.effective.project.bank.card.management.system.dto.request;

import com.effective.project.bank.card.management.system.entity.Card;
import com.effective.project.bank.card.management.system.entity.Role;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    private String email;

    private String password;

    private String fullName;

    private Set<Role> roles;

    private List<Card> cards;

}