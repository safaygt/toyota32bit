package com.safatoyota32bit.backendproject.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface verificationAuthorizationService {

    UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException;

    String generateToken(UserDetails userDetails);

    boolean validateToken(String token, UserDetails userDetails);

    boolean hasPermission(UserDetails userDetails, String permission);

}
