package com.neicake.cafeapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ProductDiscount extends Discount{


    @OneToOne
    @JoinColumn
    private Product product;

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
