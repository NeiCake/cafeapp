package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.CouponRepository;
import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Discount;
import com.neicake.cafeapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService implements ICouponService {

    @Autowired
    CouponRepository couponDao;

    @Override
    public Coupon findOneById(Long id) {
        return couponDao.findOne(id);
    }

    @Override
    public Response saveCoupon(Coupon coupon) {
        couponDao.save(coupon);
        return new Response("Coupon saved", Response.ResponseCode.SUCCESS);
    }

    @Override
    public void delete(Coupon coupon) {
        couponDao.delete(coupon);
    }
}
