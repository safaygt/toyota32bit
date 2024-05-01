package com.safatoyota32bit.backendproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="total")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"ID"})
public class total {

    @Id
    @SequenceGenerator(name = "seq_total", allocationSize = 1)
    @GeneratedValue(generator = "seq_total", strategy = GenerationType.SEQUENCE)
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
