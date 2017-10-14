package com.neicake.cafeapp.dao;

import com.neicake.cafeapp.domain.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount,Long> {
}
