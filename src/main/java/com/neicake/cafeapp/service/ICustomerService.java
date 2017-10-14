package com.neicake.cafeapp.service;

import com.neicake.cafeapp.domain.Customer;
import com.neicake.cafeapp.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ICustomerService {
    Customer save(Customer customer);
    List<Customer> getAllCustomers();
    Customer findOneById(Long id);
}
