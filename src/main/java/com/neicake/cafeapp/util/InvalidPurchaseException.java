package com.neicake.cafeapp.util;

public class InvalidPurchaseException extends RuntimeException {

	String message;

	public InvalidPurchaseException(String message) {
		this.message = message;
	}
}
