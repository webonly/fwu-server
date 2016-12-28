package com.techsun.famouswine.service;

import java.util.List;

import com.techsun.famouswine.domain.Order;
import com.techsun.famouswine.domain.OrderCount;
import com.techsun.famouswine.domain.OrderProductExt;
import com.techsun.famouswine.domain.OrderResult;

public interface OrderService {


	//根据ID获取orderList
	Order getOrderByOrderId(Integer OrderId);
	
	//根据订单状态获取商家oder状态详情
	List<OrderResult> getMerchantOrderListByStatus(Integer merchantId,Integer status,Integer pageNum,Integer pageSize);
	//获取用户所有订单
	List<OrderResult> getMerchantOrderList(Integer merchantId,Integer pageNum,Integer pageSize);	
	
	//根据订单状态查询用户订单
	List<OrderResult> getUserOrderListByStatus(Integer userId,Integer status,Integer pageNum,Integer pageSize);
	//获取用户所有订单
	List<OrderResult> getUserOrderList(Integer userId,Integer pageNum,Integer pageSize);	
	//修改订单状态
	int modifyOrderStatus(Order order);
	
	//获取今日订单和统计数据
	List<Order>  getOrderByOrderList(Integer merchantId,Integer pageNum,Integer pageSize);

	//获取当日订单和统计数据
	List<OrderResult> getBeforeOrderList(Integer merchantId,String createdDate,Integer pageNum,Integer pageSize);
	
	//获取订单详情
	List<OrderResult> selectByOrderDetails(Integer orderId);
	
	//获取 昨日成交量
	OrderCount selectByOrderTurnover(Integer merchantId,String thyDate);
	
	//获取 前一日成交量
	 List<Order> selectByOrderAnteayerTurnover(Integer merchantId,String thyDate);
	//生成订单
	int addOrder(Order order);
	
	//订单统计
	List<OrderCount> getOrderCount(Integer merchantId,String startTime,String endTime);
	
	OrderProductExt checkWine(String qrcode);
	//获取用户订单申诉
	List<OrderResult> getOrderAppealList(Integer merchantId,Integer pageNum,Integer pageSize);
	
	
}
