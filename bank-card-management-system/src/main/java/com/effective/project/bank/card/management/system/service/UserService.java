package com.effective.project.bank.card.management.system.service;

import com.effective.project.bank.card.management.system.dto.request.UserUpdateRequest;
import com.effective.project.bank.card.management.system.dto.response.UserUpdateResponse;

public interface UserService {

    UserUpdateResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest);

    Long deleteUser(Long userId);

}
