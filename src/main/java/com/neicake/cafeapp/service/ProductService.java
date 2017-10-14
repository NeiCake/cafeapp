package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.ProductRepository;
import com.neicake.cafeapp.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductSErvice{
    @Autowired
    ProductRepository productDao;

    public Product save(Product product){

        return productDao.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product findOneById(Long id) {
        return productDao.findOne(id);
    }


}
