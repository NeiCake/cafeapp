package com.neicake.cafeapp.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne
    private Product product;

    @Column
    private int amount;


    @OneToOne(targetEntity = Coupon.class)
    private Coupon coupon;

    @ManyToOne(targetEntity = ProductDiscount.class)
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

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", product=" + product +
                ", amount=" + amount +
                ", coupon=" + coupon +
                ", productDiscount=" + productDiscount +
                ", customer=" + customer +
                ", initialPricePerPiece=" + initialPricePerPiece +
                ", discountedPricePerPiece=" + discountedPricePerPiece +
                ", totalPaidWithoutDiscount=" + totalPaidWithoutDiscount +
                ", totalPaidWithDiscount=" + totalPaidWithDiscount +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;

        Purchase purchase = (Purchase) o;

        if (getAmount() != purchase.getAmount()) return false;
        if (getId() != null ? !getId().equals(purchase.getId()) : purchase.getId() != null) return false;
        if (getProduct() != null ? !getProduct().equals(purchase.getProduct()) : purchase.getProduct() != null)
            return false;
        if (getCoupon() != null ? !getCoupon().equals(purchase.getCoupon()) : purchase.getCoupon() != null)
            return false;
        if (getProductDiscount() != null ? !getProductDiscount().equals(purchase.getProductDiscount()) : purchase.getProductDiscount() != null)
            return false;
        if (getCustomer() != null ? !getCustomer().equals(purchase.getCustomer()) : purchase.getCustomer() != null)
            return false;
        return getDate() != null ? getDate().equals(purchase.getDate()) : purchase.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getProduct() != null ? getProduct().hashCode() : 0);
        result = 31 * result + getAmount();
        result = 31 * result + (getCoupon() != null ? getCoupon().hashCode() : 0);
        result = 31 * result + (getProductDiscount() != null ? getProductDiscount().hashCode() : 0);
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }
}
