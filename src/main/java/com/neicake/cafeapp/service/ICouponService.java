package com.neicake.cafeapp.service;

import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Discount;
import com.neicake.cafeapp.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICouponService{


    Coupon findOneById(Long id);
    Response saveCoupon(Coupon coupon);

    void delete(Coupon coupon);

    void deleteCoupon(Long id);
}
