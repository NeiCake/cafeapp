package com.neicake.cafeapp.util;

import com.neicake.cafeapp.dao.ProductRepository;
import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.service.IProductSErvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LongToProductConverter implements Converter<Long, Product> {

    @Autowired
    private ProductRepository dao;

    @Override
    public Product convert(Long id) {
        if(id!=null){
            return dao.findOne(id);
        }
        return null;
    }
}
