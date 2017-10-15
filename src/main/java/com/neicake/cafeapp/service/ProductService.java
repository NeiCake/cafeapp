package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.ProductDiscountRepository;
import com.neicake.cafeapp.dao.ProductRepository;
import com.neicake.cafeapp.domain.Product;
import com.neicake.cafeapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService implements IProductSErvice {
    @Autowired
    private ProductRepository productDao;
    @Autowired
    private ProductDiscountRepository productDiscountRepository;

    public Response save(Product product) {

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

    private Date compareDatesAndReturnEarliest(Date date1, Date date2) {
        if (date1.compareTo(date2) > 0) {
            return date2;
        } else {
            return date1;
        }
    }


    @Override
    public List<Product> getAllNonExpiredProductsInStock() {

        Date today = new Date();

        List<Product> list = productDao.findAll();
        List<Product> result = new ArrayList<>();
        for (Product p : list) {
            if (p.getStock() != 0 && (p.getExpirationDate() == null || today.before(p.getExpirationDate()))) {
                result.add(p);
            }

        }
        return result;
    }


    @Override
    public void completeWithDiscountBoolean(Product product) {
        product.setDiscounted(checkIfProductIsDiscounted(product));
    }


    //checks if product has active discount
    @Override
    public boolean checkIfProductIsDiscounted(Product product) {
        if (productDiscountRepository.findOneByActiveIsTrueAndProduct(product) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void completeListOfProductDiscountsWithDiscountBoolean(List<Product> products) {
        for (Product product : products) {
            completeWithDiscountBoolean(product);
        }
    }

    @Override
    public List<Product> getAllExpiredProducts() {
        List<Product> list = productDao.findAll();
        List<Product> result=new ArrayList<>();
        Date today=new Date();
        for (Product p : list) {

            if((p.getExpirationDate()!=null)&&today.after(p.getExpirationDate())){
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public List<Product> getAllStockZeroProducts() {
        return productDao.findAllByStock(0);
    }


}
