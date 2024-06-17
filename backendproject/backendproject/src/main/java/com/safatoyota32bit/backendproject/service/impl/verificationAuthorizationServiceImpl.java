package com.safatoyota32bit.backendproject.service.impl;


import com.safatoyota32bit.backendproject.entity.UserRole;
import com.safatoyota32bit.backendproject.repo.UserRoleRepository;
import com.safatoyota32bit.backendproject.repo.userRepository;
import com.safatoyota32bit.backendproject.service.verificationAuthorizationService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import com.safatoyota32bit.backendproject.util.JwtUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class verificationAuthorizationServiceImpl implements verificationAuthorizationService {

    private final userRepository UserRepository;
    private final UserRoleRepository userRoleRepository;
    private final JwtUtil jwtUtil;
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


        return jwtUtil.generateToken(userName, roles);

    }
    @Override
    public boolean validateToken(String token) {
        try {
            Claims claims = jwtUtil.extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean authorize(String token, String requiredRole) {

        try {
            Claims claims = jwtUtil.extractAllClaims(token);
            String roles = claims.get("roles", String.class);
            return roles.contains(requiredRole);
        } catch (Exception e) {
            return false;
        }
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
