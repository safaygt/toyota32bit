package com.safatoyota32bit.backendproject.service.impl;


import com.safatoyota32bit.backendproject.entity.UserRole;
import com.safatoyota32bit.backendproject.entity.user;
import com.safatoyota32bit.backendproject.repo.UserRoleRepository;
import com.safatoyota32bit.backendproject.repo.userRepository;
import com.safatoyota32bit.backendproject.service.verificationAuthorizationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Key;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class verificationAuthorizationServiceImpl implements verificationAuthorizationService {

    private final userRepository UserRepository;
    private final UserRoleRepository userRoleRepository;

    private static final String secretKey = "toyota32bit";
    private static final int validity = 5 * 60 * 1000;
    @Override
    public String generateToken(String userName) {

        Optional<user> optionalUser = UserRepository.findByUsername(userName);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        user User = optionalUser.get();
        List<UserRole> userRoles = userRoleRepository.findByUser(User);

        String roles = userRoles.stream()
                .map(userRole -> userRole.getRole().getRoleName())
                .collect(Collectors.joining(","));



        return Jwts.builder()
                .claim("username", userName)
                .claim("roles",roles)
                .claim("issuer", "toyota32bit.com")
                .claim("issuedAt",new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();


    }
    @Override
    public boolean validateToken(String token) {
        return getUserNameToken(token) != null && isExpired(token);
    }

    @Override
    public boolean authorize(String token, String requiredRole) {

        if (!validateToken(token)) {

            return false;
        }

        String name = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody().getSubject();
        Optional<user> optionalUser = UserRepository.findByUsername(name);

        if (optionalUser.isEmpty()){
            return false;
        }

        user User = optionalUser.get();
        List<UserRole> userRoles = userRoleRepository.findByUser(User);

        for (UserRole userRole: userRoles) {
            if(userRole.getRole().getRoleName().equals(requiredRole)){
                return true;
            }
        }

        return false;
    }

    public boolean isExpired(String token){
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public String getUserNameToken(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }
}
