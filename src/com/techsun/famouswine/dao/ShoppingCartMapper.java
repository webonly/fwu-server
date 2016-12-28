package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.CartProduct;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Product;
import com.techsun.famouswine.domain.ShoppingCart;
import com.techsun.famouswine.domain.ShoppingCartKey;

public interface ShoppingCartMapper {
    int deleteByPrimaryKey(ShoppingCartKey key);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(ShoppingCartKey key);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
    
    ShoppingCart getShoppingCartByKey(ShoppingCartKey key);
    
    List<Product> getShoppingCartProduct(CommonParam param);
	
	List<CartProduct> getShoppingCartList(CommonParam param);
}