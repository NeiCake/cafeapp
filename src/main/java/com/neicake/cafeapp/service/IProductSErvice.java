package com.neicake.cafeapp.service;

import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.util.Response;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductSErvice {
    Response save(Product product);
    List<Product> getAllProducts();
    Product findOneById(Long id);
    List<Product> getAllNonExpiredProductsInStock();
    void completeWithDiscountBoolean(Product product);
    boolean checkIfProductIsDiscounted(Product product);
    void completeListOfProductDiscountsWithDiscountBoolean(List<Product> products);
    List<Product> getAllExpiredProducts();
    List<Product> getAllStockZeroProducts();
}
