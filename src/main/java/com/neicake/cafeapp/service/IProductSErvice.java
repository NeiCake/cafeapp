package com.neicake.cafeapp.service;

import com.neicake.cafeapp.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductSErvice {
    Product save(Product product);
    List<Product> getAllProducts();
    Product findOneById(Long id);
}
