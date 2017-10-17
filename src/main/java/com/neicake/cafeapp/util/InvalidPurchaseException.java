package com.neicake.cafeapp.util;

public class InvalidPurchaseException extends RuntimeException {

	PurchaseError error;

	public InvalidPurchaseException(PurchaseError purchaseError) {
		super(purchaseError.toString());
		error=purchaseError;
	}

}
