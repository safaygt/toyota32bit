package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="salestype")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"saleTypeID"})
public class salestype {

    @Id
    @SequenceGenerator(name = "seq_salestype", allocationSize = 1)
    @GeneratedValue(generator = "seq_salestype", strategy = GenerationType.SEQUENCE)
    private int saleTypeID;

    @Column(name="saleType")
    private String saleType;



}
