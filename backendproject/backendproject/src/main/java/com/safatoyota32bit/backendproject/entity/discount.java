package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="discount")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"discountID"})
public class discount {

    @Id
    @SequenceGenerator(name = "seq_discount", allocationSize = 1)
    @GeneratedValue(generator = "seq_discount", strategy = GenerationType.SEQUENCE)
    private int discountID;

    @Column(name="discountName")
    private String discountName;

    @Column(name="discountPrice")
    private double discountPrice;

}
