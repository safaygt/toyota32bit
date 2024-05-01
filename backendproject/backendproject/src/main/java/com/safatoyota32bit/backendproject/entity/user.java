package com.safatoyota32bit.backendproject.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"userID"})
public class user {

    @Id
    @SequenceGenerator(name = "seq_user", allocationSize = 1)
    @GeneratedValue(generator = "seq_user", strategy = GenerationType.SEQUENCE)
    private int userID;

    @Column(name="name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Setter
    @Getter
    private boolean deleted;


}
