package com.neicake.cafeapp.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;


    @OneToMany(mappedBy = "customer")
    @Where(clause="active=true")
    private List<Coupon> coupons;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }


}
