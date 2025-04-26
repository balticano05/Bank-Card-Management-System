package com.effective.project.bank.card.management.system.dto.response;

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
public class UserUpdateResponse {

    private Long id;

    private String email;

    private String password;

    private String fullName;

    private Set<Role> roles;

    private List<Card> cards;

}
