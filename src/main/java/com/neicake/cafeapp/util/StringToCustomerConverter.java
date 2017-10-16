package com.neicake.cafeapp.util;

import com.neicake.cafeapp.domain.Customer;
import org.springframework.core.convert.converter.Converter;

public class StringToCustomerConverter implements Converter<String,Customer> {
    @Override
    public Customer convert(String s) {
        return null;
    }
}
