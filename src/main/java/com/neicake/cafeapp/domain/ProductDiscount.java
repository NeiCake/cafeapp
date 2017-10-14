package com.neicake.cafeapp.domain;

import javax.persistence.*;

@Entity
public class ProductDiscount extends Discount{


    @OneToOne
    @JoinColumn
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
