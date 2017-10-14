package com.neicake.cafeapp.dao;

import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.domain.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount,Long> {
    List<ProductDiscount> findAllByActiveIsTrue();
    ProductDiscount findOneByActiveIsTrueAndProduct(Product product);
}
