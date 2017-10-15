package com.neicake.cafeapp.dao;

import com.neicake.cafeapp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByStockGreaterThanAndExpirationDateIsNotNullAndExpirationDateIsAfter(int minimumStock, Date end);
    List<Product> findAllByStockGreaterThanAndExpirationDateIsNull(int minimumStock);
    List<Product> findAllByStock(int stock);
    List<Product> findAllByExpirationDateIsNotNullAndExpirationDateIsBefore(Date date);
    List<Product> findAllByExpirationDateIsNull();
    List<Product> findAllByStockGreaterThan(int stock);
}
