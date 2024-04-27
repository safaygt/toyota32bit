package com.safatoyota32bit.backendproject.dto;

import lombok.Data;

@Data
public class UserRoleDTO {
    private int UserRoleID;
    private userDTO user;
    private roleDTO role;
}
