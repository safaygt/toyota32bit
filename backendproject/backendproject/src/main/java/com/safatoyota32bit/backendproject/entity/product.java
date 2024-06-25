package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table (name="product")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"productID"})
public class product {

    @Id
    @SequenceGenerator(name = "seq_product", allocationSize = 1)
    @GeneratedValue(generator = "seq_product", strategy = GenerationType.SEQUENCE)
    private int productID;

    @Column(name="productName")
    private String productName;



}
