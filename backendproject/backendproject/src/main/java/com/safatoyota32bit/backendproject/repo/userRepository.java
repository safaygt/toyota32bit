package com.safatoyota32bit.backendproject.repo;

import com.safatoyota32bit.backendproject.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository <users,Integer> {

    Optional<users> findByUsername(String username);

}

