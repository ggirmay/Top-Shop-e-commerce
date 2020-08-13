package com.shop.top.shoppingcart.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentInformation implements Serializable {
	
	private String cardNumber;
	private String nameOnCard;
	private String securityDigit;
	private String expirationDate;
	private Double amount;	
	
	public PaymentInformation() {
		super();
	}

	public PaymentInformation(String cardNumber, String nameOnCard, String securityDigit, String expirationDate,
			Double amount) {
		super();
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.securityDigit = securityDigit;
		this.expirationDate = expirationDate;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PaymentInformation [cardNumber=" + cardNumber + ", nameOnCard=" + nameOnCard + ", securityDigit="
				+ securityDigit + ", expirationDate=" + expirationDate + ", amount=" + amount + "]";
	}
	
	

}
