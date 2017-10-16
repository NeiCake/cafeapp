package com.neicake.cafeapp.service;

import com.neicake.cafeapp.domain.Purchase;
import com.neicake.cafeapp.util.Response;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface IPurchaseService {
    boolean validatePurchase(Purchase purchase);
    boolean performPurchase(Purchase purchase);
    List<Purchase> listAllPurchases();
    List<Purchase> listAllPurchasesForPeriod(Date minDate, Date maxDate);
    List<Purchase> listAllPurchasesForLastDays(int days);
//    Purchase completeData(Purchase purchase);

	Purchase buildPurchaseFromString(String purchaseString);
}
