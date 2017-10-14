package com.neicake.cafeapp.domain;

import javax.persistence.*;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;


    @OneToOne
    private Product product;

    @Column
    private int amount;


    @OneToOne(targetEntity = Discount.class)
    private Discount discount;

    @ManyToOne
    private Customer customer;
}
