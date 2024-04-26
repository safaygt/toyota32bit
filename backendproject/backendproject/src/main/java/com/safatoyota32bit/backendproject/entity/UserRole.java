package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="UserRole")
@ToString


public class UserRole {

    @Id
    @Column(name="UserRoleID")
    private int UserRoleID;

    @OneToOne
    @JoinColumn(name = "FKuserID")
    private user User;

    @OneToOne
    @JoinColumn(name = "FKroleID")
    private role Role;


}
