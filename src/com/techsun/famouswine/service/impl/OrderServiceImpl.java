package com.techsun.famouswine.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.OrderMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.Order;
import com.techsun.famouswine.domain.OrderCount;
import com.techsun.famouswine.domain.OrderProductExt;
import com.techsun.famouswine.domain.OrderResult;
import com.techsun.famouswine.service.OrderService;

@Service("OrderService")
public class OrderServiceImpl implements OrderService{
	
	private OrderMapper orderMapper;
	
	public OrderMapper getOrderMapper() {
		return orderMapper;
	}
	@Autowired
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}

	@Override
	public int addOrder(Order order) {
		int count=0;
		int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//一个13位的时间戳
		String paymentID =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 订单ID
		if(order.getCode()==null||order.getCode().equals(""))
			order.setCode(paymentID);
		if(order.getStatus()==null||order.getCode().equals(""))
			order.setStatus(1);
		if(order.getCreatedDate()==null||order.getCreatedDate().equals(""))
			order.setCreatedDate(new Date());
		if(order.getUpdatedDate()==null||order.getUpdatedDate().equals(""))
			order.setUpdatedDate(new Date());
		try {
			orderMapper.updateByPrimaryKeySelective(order);
			count = orderMapper.insertSelective(order);
			if(count<0){
				throw new Exception("生成订单失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("生成订单失败！");
		}
		return count;
	}
	@Override
	public List<OrderResult> getUserOrderList(Integer userId,Integer pageNum,Integer pageSize) {
		List<OrderResult> orderResultList=new ArrayList<OrderResult>();
		CommonParam page = new CommonParam();
		page.setUserId(userId);
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		try {
			orderResultList = orderMapper.selectUserOrderList(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取用户订单失败！");
		}
		return orderResultList;
	}
	
	
	@Override
	public List<OrderResult> getMerchantOrderList(Integer merchantId,
			Integer pageNum, Integer pageSize) {
		
		List<OrderResult> orderResultList=new ArrayList<OrderResult>();
		CommonParam page = new CommonParam();
		page.setMerchantId(merchantId);
		page.setStartNum(pageNum-1);
		page.setEndNum(pageSize);
		try {
			orderResultList = orderMapper.selectMerchantOrderList(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取订单失败！");
		}
		return orderResultList;
	}
	
	
	@Override
	public List<OrderResult> getUserOrderListByStatus(Integer userId,Integer status,Integer pageNum,Integer pageSize) {
		List<OrderResult> orderResultList=new ArrayList<OrderResult>();
		CommonParam param = new CommonParam();
		param.setUserId(userId);
		param.setStatus(status);
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		try {
			orderResultList = orderMapper.selectUserOrderListByStatus(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取订单失败！");
		}
		return orderResultList;
	}
	
	
	@Override
	public List<OrderResult> getMerchantOrderListByStatus(Integer merchantId,Integer status,Integer pageNum,Integer pageSize) {
		List<OrderResult> orderResultList=new ArrayList<OrderResult>();
		CommonParam param = new CommonParam();
		param.setMerchantId(merchantId);
		param.setStatus(status);
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		try {
			orderResultList = orderMapper.selectMerchantOrderListByStatus(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取订单失败！");
		}
		return orderResultList;
	}
	
	
	
		@Override
	public Order getOrderByOrderId(Integer orderId) {
		Order order=null;
		try {
			order=orderMapper.selectByPrimaryKey(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过id获取Order数据失败！");
		}
		return order;
	}


	
	
	@Override
	public List<Order> getOrderByOrderList(Integer merchantId,Integer pageNum,Integer pageSize) {
		List<Order> order;
		CommonParam param=new CommonParam();
		param.setMerchantId(merchantId);
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		try {
			order=orderMapper.selectByOrderList(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过date获取Order今日数据失败！");
		}
		return order;
	}
	
	@Override
	public List<OrderResult> getBeforeOrderList(Integer merchantId,String createdDate,Integer pageNum,Integer pageSize) {
		List<OrderResult> order=null;
		CommonParam param=new CommonParam();
		param.setCreatedMerchantDate(createdDate);
		param.setMerchantId(merchantId);
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		try {
			order=orderMapper.selectByOrderBeforeList(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过date获取Order以往数据失败！");
		}
		return order;
	}
	
	@Override
	public List<OrderResult> selectByOrderDetails(Integer orderId) {
		List<OrderResult> order=null;
		try {
			order=orderMapper.selectByOrderDetails(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取订单失败！");
		}
		return order;
	}



	@Override
	public OrderCount selectByOrderTurnover(Integer merchantId,String thyDate) {
		OrderCount order = null;
		CommonParam param=new CommonParam();
		param.setCreatedMerchantDate(thyDate);
		param.setMerchantId(merchantId);
		try {
			order = orderMapper.selectByOrderTurnover(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过date获取昨天成交额状态数据失败！");
		}
		return order;
	}
	
	@Override
	public List<Order> selectByOrderAnteayerTurnover(Integer merchantId,String thyDate) {
		List<Order> order = null;
		CommonParam param=new CommonParam();
		param.setCreatedMerchantDate(thyDate);
		param.setMerchantId(merchantId);
		try {
			order = orderMapper.selectByOrderAnteayerTurnover(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("通过date获取前一天成交额状态数据失败！");
		}
		return order;
	}
	@Override
	public int modifyOrderStatus(Order order) {
		int count = 0;
		try {
			count = orderMapper.updateByPrimaryKeySelective(order);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("修改订单状态失败！");
		}
		
		return count;
	}
	@Override
	public List<OrderCount> getOrderCount(Integer merchantId,String startTime,String endTime) {
		List<OrderCount> order = new ArrayList<OrderCount>();
		OrderCount orderCount = new OrderCount();
		orderCount.setMerchantId(merchantId);
		orderCount.setCurrentTime(startTime);
		orderCount.setEndTime(endTime);
		try {
			order = orderMapper.findOrderCount(orderCount);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("统计订单失败！");
		}
		return order;
	}
	public OrderProductExt checkWine(String qrcode) {
		OrderProductExt ope = null;
		try {
			ope = orderMapper.checkWine(qrcode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("修改订单状态失败！");
		}
		
		return ope;
	}
	//获取用户订单申诉列表
	@Override
	public List<OrderResult> getOrderAppealList(Integer merchantId,Integer pageNum,Integer pageSize) {
		List<OrderResult> order=null;
		CommonParam param=new CommonParam();
		param.setStartNum(pageNum-1);
		param.setEndNum(pageSize);
		param.setMerchantId(merchantId);
		try {
			order = orderMapper.selectOrderAppealList(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取用户申诉列表失败！");
		}
		return order;
	}

	
}
