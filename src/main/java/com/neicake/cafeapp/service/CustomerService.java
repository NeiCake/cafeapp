package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.CustomerRepository;
import com.neicake.cafeapp.domain.Customer;
import com.neicake.cafeapp.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepository customerDao;

    @Override
    public Customer save(Customer customer) {

        return customerDao.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    @Override
    public Customer findOneById(Long id) {
        return customerDao.findOne(id);
    }
}
