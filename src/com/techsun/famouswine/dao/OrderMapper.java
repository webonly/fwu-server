package com.techsun.famouswine.dao;

import java.util.List;

import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Order;
import com.techsun.famouswine.domain.OrderCount;
import com.techsun.famouswine.domain.OrderProductExt;
import com.techsun.famouswine.domain.OrderResult;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    //用户所有订单
  	List<OrderResult> selectUserOrderList(CommonParam page);
  	//根据订单状态查询订单
  	List<OrderResult> selectUserOrderListByStatus(CommonParam page);
  	
  	List<OrderResult> selectMerchantOrderList(CommonParam page);
  	//根据订单状态查询订单
  	List<OrderResult> selectMerchantOrderListByStatus(CommonParam page);
  	
  	
    
    List<Order> selectByOrderList(CommonParam page);
    
    List<OrderResult> selectByOrderBeforeList(CommonParam param);
    
    List<OrderResult> selectByOrderDetails(Integer orderId);
    
    OrderCount selectByOrderTurnover(CommonParam param);
    
    //查询前一天的金额
    List<Order> selectByOrderAnteayerTurnover(CommonParam param);
    
    List<Order> selectByOrderDate(CommonParam param);
    //订单统计
    List<OrderCount> findOrderCount(OrderCount orderCount);
    
    OrderProductExt checkWine(String qrcode);
    //获取订单申诉列表
    List<OrderResult> selectOrderAppealList(CommonParam param);
    
}
