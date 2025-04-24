package com.effective.project.bank.card.management.system.security.service;

import com.effective.project.bank.card.management.system.entity.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public interface JwtService {

    String generateToken(String email, Set<Role> roles);

}
