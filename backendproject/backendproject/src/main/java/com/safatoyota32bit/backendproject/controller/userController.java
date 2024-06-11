package com.safatoyota32bit.backendproject.controller;

import com.safatoyota32bit.backendproject.dto.userDTO;
import com.safatoyota32bit.backendproject.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Log4j2
public class userController {

    private final userService UserService;
    @PostMapping
    public ResponseEntity<userDTO> createUser(@RequestBody userDTO UserDTO){

        log.debug("Creating user: {}", UserDTO.getUserID());
        userDTO createdUser;
        try {
            createdUser = UserService.save(UserDTO);
            log.info("User created successfully");
            return ResponseEntity.ok(createdUser);
        } catch (Exception e){
            log.error("Error occured while creating user ", e  );
            return ResponseEntity.status(500).build();
        }

    }

    @PutMapping("/{userID}")
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

    @DeleteMapping("/{id}")
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
