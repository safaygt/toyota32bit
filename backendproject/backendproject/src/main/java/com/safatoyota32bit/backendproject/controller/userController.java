package com.safatoyota32bit.backendproject.controller;

import com.safatoyota32bit.backendproject.dto.userDTO;
import com.safatoyota32bit.backendproject.service.userService;
import com.safatoyota32bit.backendproject.service.verificationAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Log4j2
public class userController {

    private final userService UserService;
    private final verificationAuthorizationService VerifyAuthService;
    @PostMapping("/create")
    public ResponseEntity<userDTO> createUser(@RequestBody userDTO userDTO) {
        log.debug("Creating user: {}", userDTO.getUserID());

        if (UserService.isUserValid(userDTO.getUsername())) {
            log.error("Username {} already exists", userDTO.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        try {
            userDTO createdUser = UserService.save(userDTO);
            log.info("User created successfully");
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            log.error("Error occurred while creating user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username) {
        log.debug("Logging in user with username: {}", username);
        try {
            if (!UserService.isUserValid(username)) {
                log.error("Invalid username: {}", username);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username");
            }

            String token = VerifyAuthService.generateToken(username);
            log.info("User logged in successfully with token");
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            log.error("Error occurred while logging in user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while logging in");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody userDTO UserDTO){
        try {

            boolean isUserValid = UserService.isUserValid(UserDTO.getUsername());
            if (!isUserValid) {
                return ResponseEntity.status(403).body("Invalid credentials");
            }
            final String token = VerifyAuthService.generateToken(UserDTO.getUsername());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Invalid credentials");
        }
    }



    @PreAuthorize("hasRole('Role_Admin')")
    @PutMapping("/update/{userID}")
    public ResponseEntity<userDTO> updateUser(@PathVariable int userID, @RequestBody userDTO UserDTO){
        log.debug("Updating user with ID {}: {}", userID, UserDTO);
        UserDTO.setUserID(userID);
        userDTO updatedUser = UserService.update(UserDTO);


        if (updatedUser != null){
            log.info("User updated successfully");
            return ResponseEntity.ok(updatedUser);
        }
        else {
            log.warn("User with ID {} not found", userID);
            return ResponseEntity.notFound().build();
        }

    }
    @PreAuthorize("hasRole('Role_Admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        log.debug("Soft deleting user with ID {}", id);
        try {
            UserService.softDelete(id);
            log.info("User has been soft deleted successfully");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error occurred while soft deleting user with ID {}", id, e);
            return ResponseEntity.status(500).build();
        }
    }
    @PreAuthorize("hasRole('Role_Admin')")
    @GetMapping
    public ResponseEntity<List<userDTO>> getAllUsers(){
        log.info("Fetching all users");
        try {
            List<userDTO> users = UserService.getAll();
            log.debug("Fetched {} users", users.size());
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("Error occurred while fetching all users", e);
            return ResponseEntity.status(500).build();
        }

    }
    @PreAuthorize("hasRole('Role_Admin')")
    @PostMapping("/{userID}/roles/{roleID}")
    public ResponseEntity<Void> assignRole(@PathVariable int userID, @PathVariable int roleID){

        log.debug("Assigning role with ID {} to user with ID {}", roleID, userID);
        try {
            UserService.assignRole(userID, roleID);
            log.info("Role assigned successfully");
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.error("Error occurred while assigning role", e);
            return ResponseEntity.notFound().build();
        }

    }

}
