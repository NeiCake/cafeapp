package com.neicake.cafeapp.domain;

import javax.persistence.*;

@Entity
public class ProductDiscount extends Discount{


    @Column
    private ProductType productType;

}
