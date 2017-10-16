package com.neicake.cafeapp.util;

import com.neicake.cafeapp.dao.CustomerRepository;
import com.neicake.cafeapp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LongToCustomerConverter implements Converter<Long,Customer> {

    @Autowired
    private CustomerRepository dao;

    @Override
    public Customer convert(Long id) {
        if(id!=null){
            return dao.findOne(id);
        }
        return null;
    }
}
