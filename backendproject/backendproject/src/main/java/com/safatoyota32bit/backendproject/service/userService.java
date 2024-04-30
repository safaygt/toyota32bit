package com.safatoyota32bit.backendproject.service;

import com.safatoyota32bit.backendproject.dto.userDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
public interface userService extends UserDetailsService {

    userDTO save(userDTO UserDTO);

    void softDelete(int id);

    List<userDTO> getAll();

    Page<userDTO> getAll(Pageable pageable);

    void assignRole(int userId, int roleId);

    userDTO update(userDTO UserDTO);

}
