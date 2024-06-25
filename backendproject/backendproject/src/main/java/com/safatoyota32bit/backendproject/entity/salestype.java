package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;


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
