package com.effective.project.bank.card.management.system.dto.mapper;

import com.effective.project.bank.card.management.system.dto.response.UserUpdateResponse;
import com.effective.project.bank.card.management.system.entity.User;

public class UserMapper {

    public static UserUpdateResponse mapEntityToUserUpdateResponse(User source) {
        return UserUpdateResponse.builder()
                .id(source.getId())
                .email(source.getEmail())
                .password(source.getPassword())
                .fullName(source.getFullName())
                .roles(source.getRoles())
                .cards(source.getCards())
                .build();
    }

}