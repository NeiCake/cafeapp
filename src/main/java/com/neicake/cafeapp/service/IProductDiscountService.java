package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.ProductDiscountRepository;
import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.domain.ProductDiscount;
import com.neicake.cafeapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface IProductDiscountService {


    ProductDiscount findOneById(Long id);

    Response saveProductDiscount(ProductDiscount discount);

    void delete(ProductDiscount discount);


}
