package com.neicake.cafeapp.domain;

import javax.persistence.*;

@Entity
public class Coupon extends Discount{


    @Column
    private ProductType productType;


    @JoinColumn
    @ManyToOne
    private Customer customer;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Coupon{" + "id --- " + this.getId()+
                "   productType=" + productType +
                ", customer=" + customer +
                '}';
    }
}
