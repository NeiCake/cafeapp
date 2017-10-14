package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.ProductRepository;
import com.neicake.cafeapp.dao.PurchaseRepository;
import com.neicake.cafeapp.domain.Purchase;
import com.neicake.cafeapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class PurchaseService implements IPurchaseService {

    @Autowired
    private PurchaseRepository purchaseDao;

    @Autowired
    private ProductRepository productDao;

    @Override
    public boolean validatePurchase(Purchase purchase) {
        if(purchase.getAmount()==0) return false;
        if(purchase.getAmount()<=purchase.getProduct().getStock()){
            return true;
        }
        return false;
    }

    @Override
    public Response performPurchase(Purchase purchase) {
        if(validatePurchase(purchase)) {
            int remainingStock=purchase.getProduct().getStock()-purchase.getAmount();
            purchase.getProduct().setStock(remainingStock);
            purchaseDao.save(purchase);
            return new Response("Purchase finalized.", Response.ResponseCode.SUCCESS);

        }
        return new Response("Purchase validation failed. Please retry.", Response.ResponseCode.INVALID_AMOUNT_TO_PURCHASE);
    }

    @Override
    public List<Purchase> listAllPurchases() {
        return purchaseDao.findAll();
    }

    @Override
    public List<Purchase> listAllPurchasesForPeriod(Date start, Date end) {
        return purchaseDao.findAllByDateBetween(start,end);
    }

    @Override
    public List<Purchase> listAllPurchasesForLastDays(int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -days);
        Date start=new Date();
        Date end= cal.getTime();
        return listAllPurchasesForPeriod(start, end);
    }

    @Override
    public Purchase completeData(Purchase purchase) {
        purchase.setTotalPaidWithDiscount(purchase.getDiscountedPricePerPiece().multiply(BigDecimal.valueOf(purchase.getAmount())));
        purchase.setTotalPaidWithoutDiscount(purchase.getInitialPricePerPiece().multiply(BigDecimal.valueOf(purchase.getAmount())));
        return purchase;
    }
}
