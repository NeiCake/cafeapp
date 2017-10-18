package com.neicake.cafeapp.dao;

import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {

    List<Coupon> findAllByActiveIsTrue();


    List<Coupon> findAllByCustomer(Customer customer);
}
