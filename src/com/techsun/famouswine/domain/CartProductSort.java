package com.techsun.famouswine.domain;

import java.util.List;

public class CartProductSort{
	

    private Integer activityId;
    
    private CartProduct cartProduct;  //单件商品
    
    private List<CartProduct> cpaList; //组合商品

    public List<CartProduct> getCpaList() {
		return cpaList;
	}

	public void setCpaList(List<CartProduct> cpaList) {
		this.cpaList = cpaList;
	}

	public CartProduct getCartProduct() {
		return cartProduct;
	}

	public void setCartProduct(CartProduct cartProduct) {
		this.cartProduct = cartProduct;
	}

	

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	

   
}