package com.safatoyota32bit.backendproject.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
@ToString
public class user {

    @Id
    @Column(name = "userID")
    private int userID;

    @Column(name="name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

}
