package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table (name="sale")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"saleID"})
public class sale {

    @Id
    @SequenceGenerator(name = "seq_sale", allocationSize = 1)
    @GeneratedValue(generator = "seq_sale", strategy = GenerationType.SEQUENCE)
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
