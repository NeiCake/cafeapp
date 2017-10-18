package com.neicake.cafeapp.service;

import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Customer;
import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ICustomerService {
    Response save(Customer customer);
    List<Customer> getAllCustomers();
    Customer findOneById(Long id);

    void deleteCustomer(Long id);

    List<Customer> getAllActiveCustomers();
}
