package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.ProductDiscountRepository;
import com.neicake.cafeapp.domain.ProductDiscount;
import com.neicake.cafeapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDiscountService implements IProductDiscountService {

    @Autowired
    private ProductDiscountRepository productDiscountDao;

    @Override
    public ProductDiscount findOneById(Long id) {
        return productDiscountDao.findOne(id);
    }

    @Override
    public Response saveProductDiscount(ProductDiscount discount) {
        productDiscountDao.save(discount);
        return new Response("Product discount saved.", Response.ResponseCode.SUCCESS);
    }

    @Override
    public void delete(ProductDiscount discount) {
        discount.setActive(false);
        productDiscountDao.save(discount);
    }
}
