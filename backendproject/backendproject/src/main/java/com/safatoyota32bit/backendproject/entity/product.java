package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="product")
@ToString

public class product {

    @Id
    @Column(name="productID")
    private int productID;

    @Column(name="productName")
    private String productName;



}
