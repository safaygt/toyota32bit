package com.safatoyota32bit.backendproject.repo;

import com.safatoyota32bit.backendproject.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository <user,Integer> {

    Optional<user> findByUsername(String username);

}

