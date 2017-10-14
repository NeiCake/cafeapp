package com.neicake.cafeapp.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class PerishableProduct extends Product {

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;

    public PerishableProduct(Product product){
        this.setId(product.getId());
        this.setDiscounted(product.isDiscounted());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setStock(product.getStock());
        this.setType(product.getType());
    }

    public PerishableProduct(){

    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
