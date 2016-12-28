package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.OrderProduct;
import com.techsun.famouswine.domain.OrderResult;


public interface OrderProductService {
	//生成商品单
	int addOrderProduct(OrderProduct orderProduct);
	//根据订单号获取订单商品信息
	List<OrderResult> getOrderProductList(Integer orderId,Integer userId);
	//修改订单商品信息
	int modifyOrderProduct(OrderProduct orderProduct);
	
}
