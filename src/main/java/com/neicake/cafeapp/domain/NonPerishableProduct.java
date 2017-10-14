package com.neicake.cafeapp.domain;

import javax.persistence.Entity;

@Entity
public class NonPerishableProduct extends Product{

    public NonPerishableProduct(Product product){
        this.setId(product.getId());
        this.setDiscounted(product.isDiscounted());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setStock(product.getStock());
        this.setType(product.getType());
    }

    public NonPerishableProduct(){

    }
}
