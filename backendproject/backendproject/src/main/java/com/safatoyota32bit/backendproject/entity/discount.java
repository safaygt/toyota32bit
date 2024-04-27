package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="discount")
@ToString
public class discount {

    @Id
    @Column(name = "discountID")
    private int discountID;

    @Column(name="discountName")
    private String discountName;

    @Column(name="discountPrice")
    private double discountPrice;

}
