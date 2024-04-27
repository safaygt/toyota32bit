package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="total")
@ToString

public class total {

    @Id
    @Column(name="ID")
    private int ID;

    @Column(name="totalPrice")
    private double totalPrice;

    @Column(name="change")
    private double change;

    @Column(name="pay")
    private double pay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @OneToOne
    @JoinColumn(name="FKdiscountID")
    private discount Discount;

    @OneToMany
    @JoinColumn(name = "FKsaleID")
    private sale Sale;

}
