package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="sale")
@ToString

public class sale {

    @Id
    @Column(name="saleID")
    private int saleID;

    @OneToOne
    @JoinColumn(name = "FKproductID")
    private product Product;

    @OneToOne
    @JoinColumn(name = "FKsaleTypeID")
    private salestype saleType;

    @OneToOne
    @JoinColumn(name = "FKpriceID")
    private price Price;

    @Column(name="count")
    private int count;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

}
