package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="role")
@ToString

public class role {

    @Id
    @Column(name="roleID")
    private int roleID;

    @Column(name="roleName")
    private String roleName;

}
