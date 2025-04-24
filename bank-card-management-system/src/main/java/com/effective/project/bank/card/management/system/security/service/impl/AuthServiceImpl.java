package com.effective.project.bank.card.management.system.security.service.impl;

import com.effective.project.bank.card.management.system.entity.User;
import com.effective.project.bank.card.management.system.repository.RoleRepository;
import com.effective.project.bank.card.management.system.repository.UserRepository;
import com.effective.project.bank.card.management.system.security.dto.UserAuthRequest;
import com.effective.project.bank.card.management.system.security.dto.UserAuthResponse;
import com.effective.project.bank.card.management.system.security.dto.UserRegisterRequest;
import com.effective.project.bank.card.management.system.security.dto.UserRegisterResponse;
import com.effective.project.bank.card.management.system.security.service.AuthService;
import com.effective.project.bank.card.management.system.security.service.JwtService;
import com.effective.project.bank.card.management.system.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {

        User user = User.builder()
                .email(userRegisterRequest.getEmail())
                .password(userRegisterRequest.getPassword())
                .fullName(userRegisterRequest.getFullName())
                .roles(roleRepository.findByName("USER"))
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user.getEmail());


        return UserRegisterResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public UserAuthResponse authenticate(UserAuthRequest userAuthRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthRequest.getEmail(),
                        userAuthRequest.getPassword()
                )
        );

        User user = userRepository.findByEmail(userAuthRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found with name: " + userAuthRequest.getEmail()));

        String jwtToken = jwtService.generateToken(user.getEmail());


        return UserAuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
