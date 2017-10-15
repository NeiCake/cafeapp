package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.CouponRepository;
import com.neicake.cafeapp.dao.CustomerRepository;
import com.neicake.cafeapp.domain.Coupon;
import com.neicake.cafeapp.domain.Customer;
import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepository customerDao;

    @Autowired
    private CouponRepository couponDao;
    @Override
    public Response save(Customer customer) {
        couponDao.save(customer.getCoupons());

        customerDao.save(customer);
        return new Response("Customer saved.", Response.ResponseCode.SUCCESS);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer>list=customerDao.findAll();
        return list;
    }


    @Override
    public Customer findOneById(Long id) {
        return customerDao.findOne(id);
    }

    @Override
    public void delete(Coupon coupon) {
        couponDao.delete(coupon);
    }

    @Override
    public void deleteCustomer(Long id) {

        Customer c=customerDao.findOne(id);
        c.setActive(false);
        customerDao.save(c);
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return customerDao.findAllByActive(true);
    }

}
