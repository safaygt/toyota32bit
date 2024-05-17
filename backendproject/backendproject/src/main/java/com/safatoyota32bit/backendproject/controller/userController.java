package com.safatoyota32bit.backendproject.controller;

import com.safatoyota32bit.backendproject.dto.userDTO;
import com.safatoyota32bit.backendproject.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class userController {

    private final userService UserService;
    @PostMapping
    public ResponseEntity<userDTO> createUser(@RequestBody userDTO UserDTO){

        userDTO createdUser = UserService.save(UserDTO);
        return ResponseEntity.ok(createdUser);

    }

    @PutMapping("/{userID}")
    public ResponseEntity<userDTO> updateUser(@PathVariable int userID, @RequestBody userDTO UserDTO){
        UserDTO.setUserID(userID);
        userDTO updatedUser = UserService.update(UserDTO);

        if (updatedUser != null){

            return ResponseEntity.ok(updatedUser);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        UserService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<userDTO>> getAllUsers(){

        List<userDTO> users = UserService.getAll();
        return ResponseEntity.ok(users);

    }

    @PostMapping("/{userID}/roles/{roleID}")
    public ResponseEntity<Void> assignRole(@PathVariable int userID, @PathVariable int roleID){

        try {

            UserService.assignRole(userID, roleID);
            return ResponseEntity.ok().build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();

        }

    }

}
