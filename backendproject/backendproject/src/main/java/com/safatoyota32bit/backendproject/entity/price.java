package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="price")
@ToString

public class price {

    @Id
    @Column(name="priceID")
    private int priceID;

    private double price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private double discount;

    @OneToMany
    @JoinColumn(name = "FKproductID")
    private product Product;


}
