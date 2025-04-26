package com.effective.project.bank.card.management.system.security.controller;

import com.effective.project.bank.card.management.system.security.dto.UserAuthRequest;
import com.effective.project.bank.card.management.system.security.dto.UserAuthResponse;
import com.effective.project.bank.card.management.system.security.dto.UserRegisterRequest;
import com.effective.project.bank.card.management.system.security.dto.UserRegisterResponse;
import com.effective.project.bank.card.management.system.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponse register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return authService.register(userRegisterRequest);
    }

    @PostMapping("/authenticate")
    public UserAuthResponse authenticate(@RequestBody UserAuthRequest userAuthRequest) {
        return authService.authenticate(userAuthRequest);
    }

}
