package com.safatoyota32bit.backendproject.service.impl;

import com.safatoyota32bit.backendproject.dto.userDTO;
import com.safatoyota32bit.backendproject.entity.UserRole;
import com.safatoyota32bit.backendproject.entity.role;
import com.safatoyota32bit.backendproject.entity.users;
import com.safatoyota32bit.backendproject.repo.UserRoleRepository;
import com.safatoyota32bit.backendproject.repo.roleRepository;
import com.safatoyota32bit.backendproject.repo.userRepository;
import com.safatoyota32bit.backendproject.service.userService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class userServiceImpl implements userService {

    private final userRepository UserRepository;
    private final roleRepository RoleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public userDTO save(userDTO UserDTO) {

        users User = new users();
        User.setName(UserDTO.getName());
        User.setLastName(UserDTO.getLastName());
        final users UserDB = UserRepository.save(User);
        UserDTO.setUserID(UserDB.getUserID());

        return UserDTO;
    }

    @Override
    public void softDelete(int userID) {

    Optional<users> userOptional = UserRepository.findById(userID);
    userOptional.ifPresent(user -> {

        user.setDeleted(true);
        //user.isDeleted();
        UserRepository.save(user);
    });

        List<UserRole> userRoles = userRoleRepository.findByUserIDAndNotDeleted(userID);
        for (UserRole userRole : userRoles) {
            userRole.setDeleted(true);
            userRoleRepository.save(userRole);
        }

    }

    @Override
    public List<userDTO> getAll() {

        List<users> usersList = UserRepository.findAll();
        List<userDTO> userDTOList = new ArrayList<>();
        usersList.forEach(item ->
                {
                    userDTO UserDTO = new userDTO();
                    UserDTO.setUserID(item.getUserID());
                    UserDTO.setName(item.getName());
                    UserDTO.setLastName(item.getLastName());
                    userDTOList.add(UserDTO);
                }

        );

        return userDTOList;
    }

    @Override
    public Page<userDTO> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public void assignRole(int userId, int roleId) {
        Optional<users> userOptional = UserRepository.findById(userId);
        Optional<role> roleOptional = RoleRepository.findById(roleId);

        if (userOptional.isPresent() && roleOptional.isPresent()) {
            users User = userOptional.get();
            role Role = roleOptional.get();


            UserRole userRole = new UserRole();
            userRole.setUser(User);
            userRole.setRole(Role);
            userRoleRepository.save(userRole);
        } else {

            throw new RuntimeException("Kullanıcı veya rol bulunamadı");
        }

    }

    @Override
    public userDTO update(userDTO UserDTO) {
        Optional<users> userOptional = UserRepository.findById(UserDTO.getUserID());
        if(userOptional.isPresent()){
            users existingUser = userOptional.get();
            existingUser.setName(UserDTO.getName());
            existingUser.setLastName(UserDTO.getLastName());
            UserRepository.save(existingUser);
            return convertToDTO(existingUser);
        }else {

            return null;
        }

    }

    private userDTO convertToDTO(users userEntity) {
        userDTO UserDTO = new userDTO();
        UserDTO.setUserID(userEntity.getUserID());
        UserDTO.setName(userEntity.getName());
        UserDTO.setLastName(userEntity.getLastName());

        return UserDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<users> userOptional = UserRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        users user = userOptional.get();
        String UserName = user.getName() + user.getLastName();
        List<UserRole> userRoles = userRoleRepository.findByUser(user);
        List<SimpleGrantedAuthority> authorities = userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getRoleName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(UserName, "", authorities);
    }

}
