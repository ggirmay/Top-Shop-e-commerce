package com.top.shop.user.exception;

public class BadInputRequestException extends RuntimeException {

	private static final long serialVersionUID = 5820918555350604651L;

	public BadInputRequestException(String message) {
		super(message);
	}

}
