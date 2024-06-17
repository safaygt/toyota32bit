package com.safatoyota32bit.backendproject.repo;

import com.safatoyota32bit.backendproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository <User,Integer> {

    Optional<User> findByUsername(String username);

}

