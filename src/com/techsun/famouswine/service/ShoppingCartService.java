package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.CartProduct;
import com.techsun.famouswine.domain.ShoppingCart;
import com.techsun.famouswine.domain.Product;




/**
 * 
* @ClassName: ShoppingCartService  
* @author zhanglijun
* @date 2015-8-15 1:52:33 
*
 */
public interface ShoppingCartService {
	
	
	int addProductToCart(ShoppingCart shoppingCart);
	
	int updateProductToCart(ShoppingCart shoppingCart);
	
	ShoppingCart getShoppingCartByKey(int userId, int activityId, int productId, int merchantId);
	List<CartProduct> getShoppingCartList(int userId, int pageNum, int pageSize);
}
