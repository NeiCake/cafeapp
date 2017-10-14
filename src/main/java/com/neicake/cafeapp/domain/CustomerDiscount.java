package com.neicake.cafeapp.domain;

import javax.persistence.*;

@Entity
public class CustomerDiscount extends Discount{


    @JoinColumn
    @ManyToOne
    private Customer customer;

}
