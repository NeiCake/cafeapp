package com.neicake.cafeapp.service;

import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductSErvice {
    Response save(Product product);
    List<Product> getAllProducts();
    Product findOneById(Long id);
    List<Product> getAllNonExpiredProductsInStock();
}
