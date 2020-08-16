package com.shop.top.shoppingcart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
	
	private ProductDTO product;
	private int quantity;
	
	public CartItem() {
		super();
	}

	public CartItem(ProductDTO product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	
}
