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

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("Loading user by email: {}", email);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Card not found."));

        return new CustomUserDetails(user);
    }

}