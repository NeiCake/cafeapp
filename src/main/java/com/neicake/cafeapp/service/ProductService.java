package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.ProductRepository;
import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService implements IProductSErvice{
    @Autowired
    ProductRepository productDao;

    public Response save(Product product){

        productDao.save(product);
        return new Response("Product saved.", Response.ResponseCode.SUCCESS);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product findOneById(Long id) {
        return productDao.findOne(id);
    }

    @Override
    public List<Product> getAllNonExpiredProductsInStock() {
        List<Product> list=productDao.findAllByStockGreaterThanAndExpirationDateIsNotNullAndExpirationDateIsBefore(0,new Date());
        list.addAll(productDao.findAllByStockGreaterThanAndExpirationDateIsNull(0));
        return list;
    }


}
