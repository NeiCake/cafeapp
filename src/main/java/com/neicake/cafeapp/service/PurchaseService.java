package com.neicake.cafeapp.service;

import com.neicake.cafeapp.dao.*;
import com.neicake.cafeapp.domain.*;
import com.neicake.cafeapp.util.InvalidPurchaseException;
import com.neicake.cafeapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PurchaseService implements IPurchaseService {

	@Autowired
	private PurchaseRepository purchaseDao;

	@Autowired
	private ProductRepository productDao;

	@Autowired
	private ProductDiscountRepository productDiscountDao;

	@Autowired
	private CouponRepository couponDao;

	@Autowired
	private CustomerRepository customerDao;

	@Override
	public boolean validatePurchase(Purchase purchase) {
		if (purchase.getAmount() == 0) return false;
		if (purchase.getAmount() <= purchase.getProduct().getStock()) {
			return true;
		}
		System.out.println("not enough stock");
		return false;
	}

	@Override
	public boolean performPurchase(Purchase purchase) {
		if (validatePurchase(purchase)) {
			int remainingStock = purchase.getProduct().getStock() - purchase.getAmount();
			purchase.getProduct().setStock(remainingStock);
			productDao.save(purchase.getProduct());
			purchaseDao.save(purchase);
			return true;

		}
		return false;
	}

	@Override
	public List<Purchase> listAllPurchases() {
		List<Purchase> list = purchaseDao.findAll();
//        for(Purchase p:list){
//	        p=completeData(p);
//        }
		return list;
	}

	@Override
	public List<Purchase> listAllPurchasesForPeriod(Date start, Date end) {

		List<Purchase> list = purchaseDao.findAllByDateBetween(start, end);
//	    for(Purchase p:list){
//		    p=completeData(p);
//	    }
		return list;
	}

	@Override
	public List<Purchase> listAllPurchasesForLastDays(int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -days);
		Date start = new Date();
		Date end = cal.getTime();
		return listAllPurchasesForPeriod(start, end);
	}

  /*  @Override
    public Purchase completeData(Purchase purchase) {
//    	purchase.setDiscountedPricePerPiece();
//    	purchase.setInitialPricePerPiece(purchase.getProduct().getPrice()*purchase.);
        purchase.setTotalPaidWithDiscount(purchase.getDiscountedPricePerPiece().multiply(BigDecimal.valueOf(purchase.getAmount())));
        purchase.setTotalPaidWithoutDiscount(purchase.getInitialPricePerPiece().multiply(BigDecimal.valueOf(purchase.getAmount())));
        return purchase;
    }*/

	@Override
	public Purchase buildPurchaseFromString(String purchaseString) {
		String[] array = purchaseString.split("\\+");
		System.out.println("array length is: " + array.length);
		System.out.println(array);
		if (array.length != 6) {
			System.out.println("MISSING PARAMETER");
			throw new InvalidPurchaseException("MISSING A PARAMETER");
		}
		Purchase purchase = new Purchase();
		if (!array[0].equals("0")) {
			Product product = productDao.getOne(Long.valueOf(array[0]));
			purchase.setProduct(product);
		}
		if (!array[1].equals("0")) {
			ProductDiscount productDiscount = productDiscountDao.findOne(Long.valueOf(array[1]));
			purchase.setProductDiscount(productDiscount);
		}
		if (!array[2].equals("0")) {
			try{
				Customer customer = customerDao.findOne(Long.valueOf(array[2]));
				purchase.setCustomer(customer);
			}catch (NumberFormatException e){
				//leaving customer null if there is no customer
			}
		}
		if (!array[3].equals("0")) {

			try{
				Coupon coupon = couponDao.findOne(Long.valueOf(array[3]));
				purchase.setCoupon(coupon);
			}catch (NumberFormatException e){
				//leaving coupon null if there is no coupon
			}
		}

		if (array[4].equals("")) {
			System.out.println("no amount was input");
			throw new InvalidPurchaseException("EMPTY STRING AMOUNT");
		}
		purchase.setAmount(Integer.parseInt(array[4]));

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date result = null;
		try {
			result = df.parse(array[5]);
		} catch (ParseException e) {
			System.out.println("could not parse date");
			throw new InvalidPurchaseException("INVALID DATE");

		}
		purchase.setDate(result);

		return purchase;
	}
}
