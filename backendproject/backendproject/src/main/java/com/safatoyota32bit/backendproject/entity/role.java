package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="role")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"roleID"})
public class role {

    @Id
    @SequenceGenerator(name = "seq_role", allocationSize = 1)
    @GeneratedValue(generator = "seq_role", strategy = GenerationType.SEQUENCE)
    private int roleID;

    @Column(name="roleName")
    private String roleName;

}
