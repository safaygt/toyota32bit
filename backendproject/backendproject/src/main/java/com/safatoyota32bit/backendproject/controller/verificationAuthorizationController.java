package com.safatoyota32bit.backendproject.controller;


import com.safatoyota32bit.backendproject.service.verificationAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@RequestMapping("/verification")
public class verificationAuthorizationController {

    private final verificationAuthorizationService VerifyAuthService;

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestParam String username){

        String token = VerifyAuthService.generateToken(username);
        return ResponseEntity.ok(token);

    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> ValidateToken(@RequestParam String token){

        boolean isValid = VerifyAuthService.validateToken(token);
        return ResponseEntity.ok(isValid);

    }

    @PostMapping("/authorize")
    public ResponseEntity<Boolean> authorize(@RequestParam String token, @RequestParam String requiredRole){

        boolean isAuthorized = VerifyAuthService.authorize(token, requiredRole);
        return ResponseEntity.ok(isAuthorized);

    }


}
