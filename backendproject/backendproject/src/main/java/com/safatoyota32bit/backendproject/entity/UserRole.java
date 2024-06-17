package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FKuserID")
    private users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FKroleID")
    private role Role;


    @Setter
    @Getter
    private boolean deleted;



}
