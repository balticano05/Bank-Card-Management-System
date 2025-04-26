package com.effective.project.bank.card.management.system.security.service.impl;

import com.effective.project.bank.card.management.system.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CustomSecurityService {

    public boolean isOwner(Authentication authentication, Long targetUserId) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getId().equals(targetUserId);
    }

}