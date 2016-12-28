package com.techsun.famouswine.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.techsun.famouswine.dao.OrderProductMapper;
import com.techsun.famouswine.domain.CommonParam;
import com.techsun.famouswine.domain.OrderProduct;
import com.techsun.famouswine.domain.OrderResult;
import com.techsun.famouswine.service.OrderProductService;
@Service("OrderProductService")
public class OrderProductServiceImpl implements OrderProductService {
	private OrderProductMapper orderProductMapper;
	
	public OrderProductMapper getOrderProductMapper() {
		return orderProductMapper;
	}
	@Autowired
	public void setOrderProductMapper(OrderProductMapper orderProductMapper) {
		this.orderProductMapper = orderProductMapper;
	}

	@Override
	public int addOrderProduct(OrderProduct orderProduct) {
		int count=0;
		if(orderProduct.getCreatedDate()==null||orderProduct.getCreatedDate().equals("")){
			orderProduct.setCreatedDate(new Date());
		}
		if(orderProduct.getUpdatedDate()==null||orderProduct.getUpdatedDate().equals("")){
			orderProduct.setUpdatedDate(new Date());
		}
		try {
			orderProductMapper.updateByPrimaryKeySelective(orderProduct);
			count=orderProductMapper.insertSelective(orderProduct);
			if(count<0){
				throw new Exception("自动生成失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("自动生成失败！");
		}
		return count;
	}
	@Override
	public List<OrderResult> getOrderProductList(Integer orderId,
			Integer userId) {
		List<OrderResult> orderProduct = new ArrayList<OrderResult>();
		CommonParam page = new CommonParam();
		page.setUserId(userId);;
		page.setProductId(orderId);
		try {
			orderProduct = orderProductMapper.findOrderProductList(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取订单商品信息失败！");
		}
		return orderProduct;
	}
	@Override
	public int modifyOrderProduct(OrderProduct orderProduct) {
		int count = 0;
		try {
			count = orderProductMapper.updateByPrimaryKeySelective(orderProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("修改订单商品信息失败！");
		}
		return count;
	}

}
