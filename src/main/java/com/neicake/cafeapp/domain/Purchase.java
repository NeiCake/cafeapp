package com.neicake.cafeapp.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;


    @OneToOne
    private Product product;

    @Column
    private int amount;


    @OneToOne(targetEntity = Coupon.class)
    private Coupon coupon;

    @OneToOne(targetEntity = ProductDiscount.class)
    private ProductDiscount productDiscount;

    @ManyToOne
    private Customer customer;


    @Column
    private BigDecimal initialPricePerPiece;

    @Column
    private BigDecimal discountedPricePerPiece;

    @Transient
    private BigDecimal totalPaidWithoutDiscount;

    @Transient private BigDecimal totalPaidWithDiscount;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getInitialPricePerPiece() {
        return initialPricePerPiece;
    }

    public void setInitialPricePerPiece(BigDecimal initialPricePerPiece) {
        this.initialPricePerPiece = initialPricePerPiece;
    }

    public BigDecimal getDiscountedPricePerPiece() {
        return discountedPricePerPiece;
    }

    public void setDiscountedPricePerPiece(BigDecimal discountedPricePerPiece) {
        this.discountedPricePerPiece = discountedPricePerPiece;
    }

    public BigDecimal getTotalPaidWithoutDiscount() {
        return totalPaidWithoutDiscount;
    }

    public void setTotalPaidWithoutDiscount(BigDecimal totalPaidWithoutDiscount) {
        this.totalPaidWithoutDiscount = totalPaidWithoutDiscount;
    }

    public BigDecimal getTotalPaidWithDiscount() {
        return totalPaidWithDiscount;
    }

    public void setTotalPaidWithDiscount(BigDecimal totalPaidWithDiscount) {
        this.totalPaidWithDiscount = totalPaidWithDiscount;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public ProductDiscount getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(ProductDiscount productDiscount) {
        this.productDiscount = productDiscount;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
