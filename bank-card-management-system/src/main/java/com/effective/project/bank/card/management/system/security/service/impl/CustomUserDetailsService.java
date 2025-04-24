package com.effective.project.bank.card.management.system.security.service.impl;

import com.effective.project.bank.card.management.system.entity.User;
import com.effective.project.bank.card.management.system.repository.UserRepository;
import com.effective.project.bank.card.management.system.security.CustomUserDetails;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("Loading user by email: {}", email);

        Optional<User> user = userRepository.findByEmail(email);

        return user.map(u -> {

            log.info("Found user with roles: {}", u.getRoles());

            return new CustomUserDetails(u);
        }).orElseThrow(() -> {

            log.error("User not found with email: {}", email);

            return new EntityNotFoundException("User not found with name: " + email);
        });
    }

}