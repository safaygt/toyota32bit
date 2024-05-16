package com.safatoyota32bit.backendproject.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface verificationAuthorizationService {

    String generateToken(String userName);
    boolean validateToken(String token);
    boolean authorize(String token, String requiredRole);
}
