package com.techsun.famouswine.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.OrderResult;
import com.techsun.famouswine.service.OrderProductService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;


@Controller
public class OrderProductController {
	private OrderProductService orderProductService;

	public OrderProductService getOrderProductService() {
		return orderProductService;
	}
	@Autowired
	public void setOrderProductService(OrderProductService orderProductService) {
		this.orderProductService = orderProductService;
	}
	
	
	
	@RequestMapping(value="/orderProduct/getOrderProduct")
	public @ResponseBody
	Object getOrderProduct(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String orderId = request.getParameter("orderId");
		String userId = request.getParameter("userId");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("获取用户信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(orderId==null||orderId.equals("")){
			reInfo.setErrorMessage("获取订单信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		List<OrderResult> orderProductList = new ArrayList<OrderResult>();
		try {
			orderProductList=orderProductService.getOrderProductList(Integer.parseInt(orderId), Integer.parseInt(userId));
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取订单商品失败！");
			reInfo.setErrorCode("10002");
			return reInfo;
		}
		rsInfo.setErrorMessage("获取订单商品成功！");
		rsInfo.setResult(orderProductList);
		return rsInfo;
	}
}
