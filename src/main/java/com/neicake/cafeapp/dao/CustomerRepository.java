package com.neicake.cafeapp.dao;

import com.neicake.cafeapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAllByActive(boolean active);
    List<Customer> findAllByActiveIsTrue();
}
