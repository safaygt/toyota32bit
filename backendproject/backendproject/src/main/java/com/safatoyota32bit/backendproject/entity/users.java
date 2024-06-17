package com.safatoyota32bit.backendproject.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"userID"})
public class users {
    @Id
    @SequenceGenerator(name = "seq_users", allocationSize = 1)
    @GeneratedValue(generator = "seq_users", strategy = GenerationType.SEQUENCE)
    private int userID;

    @Column(name="name")
    private String name;

    @Column(name = "lastName")
    private String lastName;


    @Column(name = "username", unique = true)
    private String username;


    @Setter
    @Getter
    private boolean deleted;


    @PrePersist
    @PreUpdate
    private void generateUsername() {
        this.username = this.name.toLowerCase() + this.lastName.toLowerCase();
    }


}
