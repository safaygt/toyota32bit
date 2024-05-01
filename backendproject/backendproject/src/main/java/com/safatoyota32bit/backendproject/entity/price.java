package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="price")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"priceID"})
public class price {

    @Id
    @SequenceGenerator(name = "seq_price", allocationSize = 1)
    @GeneratedValue(generator = "seq_price", strategy = GenerationType.SEQUENCE)
    private int priceID;

    private double price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @OneToOne
    @JoinColumn(name = "FKdiscountID")
    private discount Discount;

    @OneToMany
    @JoinColumn(name = "FKproductID")
    private product Product;


}
