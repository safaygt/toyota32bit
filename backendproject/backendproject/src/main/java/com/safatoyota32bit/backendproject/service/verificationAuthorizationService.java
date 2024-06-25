package com.safatoyota32bit.backendproject.service;


public interface verificationAuthorizationService {

    String generateToken(String userName);
    boolean validateToken(String token);
    boolean authorize(String token, String requiredRole);
}
