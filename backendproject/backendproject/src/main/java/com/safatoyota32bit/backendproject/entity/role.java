package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;

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
