package com.effective.project.bank.card.management.system.security.service;

public interface JwtService {

    String generateToken(String username);

}
