package com.neicake.cafeapp.domain;

import javax.persistence.*;
import java.util.List;
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;


    @OneToMany
    private List<CustomerDiscount> coupons;

    @OneToMany
    private List<Purchase> purchases;

}
