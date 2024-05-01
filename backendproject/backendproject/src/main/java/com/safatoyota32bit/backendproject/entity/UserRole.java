package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="UserRole")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"UserRoleID"})
public class UserRole {

    @Id
    @SequenceGenerator(name = "seq_UserRole", allocationSize = 1)
    @GeneratedValue(generator = "seq_UserRole", strategy = GenerationType.SEQUENCE)
    private int UserRoleID;

    @OneToOne
    @JoinColumn(name = "FKuserID")
    private user User;

    @OneToOne
    @JoinColumn(name = "FKroleID")
    private role Role;


    @Setter
    @Getter
    private boolean deleted;



}
