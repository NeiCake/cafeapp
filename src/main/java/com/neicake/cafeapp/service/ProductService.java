package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.ProductDiscountRepository;
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
    private ProductRepository productDao;
    @Autowired
    private ProductDiscountRepository productDiscountRepository;

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

    @Override
    public void completeWithDiscountBoolean(Product product) {
        product.setDiscounted(checkIfProductIsDiscounted(product));
    }


    //checks if product has active discount
    @Override
    public boolean checkIfProductIsDiscounted(Product product) {
        if(productDiscountRepository.findOneByActiveIsTrueAndProduct(product)!=null){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void completeListOfProductDiscountsWithDiscountBoolean(List<Product> products) {
        for(Product product:products){
            completeWithDiscountBoolean(product);
        }
    }


}
