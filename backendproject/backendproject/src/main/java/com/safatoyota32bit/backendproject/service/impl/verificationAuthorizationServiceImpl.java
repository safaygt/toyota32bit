package com.safatoyota32bit.backendproject.service.impl;


import com.safatoyota32bit.backendproject.entity.UserRole;
import com.safatoyota32bit.backendproject.entity.users;
import com.safatoyota32bit.backendproject.repo.UserRoleRepository;
import com.safatoyota32bit.backendproject.repo.userRepository;
import com.safatoyota32bit.backendproject.service.verificationAuthorizationService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import com.safatoyota32bit.backendproject.util.JwtUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


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
    @Override
    public String generateToken(String userName) {
        Optional<users> optionalUser = UserRepository.findByUsername(userName);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        users User = optionalUser.get();
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
}
