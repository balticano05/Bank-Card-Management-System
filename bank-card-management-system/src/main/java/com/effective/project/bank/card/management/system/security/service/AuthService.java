package com.effective.project.bank.card.management.system.security.service;

import com.effective.project.bank.card.management.system.security.dto.UserAuthRequest;
import com.effective.project.bank.card.management.system.security.dto.UserAuthResponse;
import com.effective.project.bank.card.management.system.security.dto.UserRegisterRequest;
import com.effective.project.bank.card.management.system.security.dto.UserRegisterResponse;

public interface AuthService {

    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

    UserAuthResponse authenticate(UserAuthRequest userAuthRequest);

}
