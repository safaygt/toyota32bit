package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="salestype")
@ToString

public class salestype {

    @Id
    @Column(name="saleTypeID")
    private int saleTypeID;

    @Column(name="saleType")
    private String saleType;



}
