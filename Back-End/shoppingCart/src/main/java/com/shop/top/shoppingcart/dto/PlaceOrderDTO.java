package com.shop.top.shoppingcart.dto;

import com.shop.top.shoppingcart.models.Orders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderDTO {

	private OrderDTO order;
	private PaymentInformation card;
	
	public PlaceOrderDTO() {
		super();
	}
	
	public PlaceOrderDTO(OrderDTO order, PaymentInformation card) {
		super();
		this.order = order;
		this.card = card;
	}
	
	
	
}
