package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.CouponRepository;
import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Customer;
import com.neicake.cafeapp.domain.Discount;
import com.neicake.cafeapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService implements ICouponService {

    @Autowired
    private CouponRepository couponDao;

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
        coupon.setActive(false);
        couponDao.save(coupon);

    }

    @Override
    public void deleteCoupon(Long id) {

        Coupon coupon=couponDao.findOne(id);
        coupon.setActive(false);
        couponDao.save(coupon);
    }

    @Override
    public List<Coupon> findAllByCustomer(Customer customer) {
        return couponDao.findAllByCustomer(customer);
    }

    @Override
    public void deleteAllCouponsForCustomer(Customer customer) {
        List<Coupon> coupons=couponDao.findAllByCustomer(customer);
        for(Coupon c:coupons){
            delete(c);
        }
    }
}
