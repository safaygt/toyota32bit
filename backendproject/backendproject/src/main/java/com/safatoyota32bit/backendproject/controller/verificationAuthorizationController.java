package com.safatoyota32bit.backendproject.controller;


import com.safatoyota32bit.backendproject.service.verificationAuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/verification")
public class verificationAuthorizationController {

    private final verificationAuthorizationService VerifyAuthService;

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestParam String username){

        log.debug("Generating token for username: {}", username);
        String token;
        try {
            token = VerifyAuthService.generateToken(username);
            log.info("Token generated successfully");
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            log.error("Error occurred while generating token", e);
            return ResponseEntity.status(500).build();
        }

    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> ValidateToken(@RequestParam String token){

        log.debug("Validating token: {}", token);
        boolean isValid;
        try {
            isValid= VerifyAuthService.validateToken(token);
            if (isValid) {
                log.info("Token validation successful");
            } else {
                log.warn("Token validation failed");
            }
            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            log.error("Error occurred while validating token", e);
            return ResponseEntity.status(500).build();
        }

    }

    @PostMapping("/authorize")
    public ResponseEntity<Boolean> authorize(@RequestParam String token, @RequestParam String requiredRole){

        log.debug("Authorizing token: {} for role: {}", token, requiredRole);
        boolean isAuthorized;
        try {
            isAuthorized = VerifyAuthService.authorize(token, requiredRole);
            if (isAuthorized) {
                log.info("Authorization successful");
            } else {
                log.warn("Authorization failed for role: {}", requiredRole);
            }
            return ResponseEntity.ok(isAuthorized);
        } catch (Exception e) {
            log.error("Error occurred while authorizing token", e);
            return ResponseEntity.status(500).build();
        }

    }


}
