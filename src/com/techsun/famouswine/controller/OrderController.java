package com.techsun.famouswine.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Order;
import com.techsun.famouswine.domain.OrderCount;
import com.techsun.famouswine.domain.OrderProduct;
import com.techsun.famouswine.domain.OrderProductExt;
import com.techsun.famouswine.domain.OrderResult;
import com.techsun.famouswine.domain.OrderSort;
import com.techsun.famouswine.domain.Schedule;
import com.techsun.famouswine.service.OrderProductService;
import com.techsun.famouswine.service.OrderService;
import com.techsun.famouswine.service.ScheduleService;
import com.techsun.famouswine.util.DateUtil;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class OrderController {
	private OrderService orderService;
	private OrderProductService orderProductService;
	private ScheduleService scheduleService;

	public ScheduleService getScheduleService() {
		return scheduleService;
	}
	@Autowired
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	public OrderProductService getOrderProductService() {
		return orderProductService;
	}
	@Autowired
	public void setOrderProductService(OrderProductService orderProductService) {
		this.orderProductService = orderProductService;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// 自动生成订单
	@RequestMapping(value="/order/generateOrder")
	public @ResponseBody
	Object addOrder(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String merchantId = request.getParameter("merchantId");
		String[] productId = request.getParameterValues("productId");
		String[] productNum = request.getParameterValues("productNum");
		String[] activityId = request.getParameterValues("activityId");
		String distributionMode = request.getParameter("distributionMode");
		String arrivalTime = request.getParameter("arrivalTime");
		String userMessage = request.getParameter("userMessage");

		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("获取用户信息失败！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		if(merchantId==null||merchantId.equals("")){
			reInfo.setErrorMessage("获取商家信息失败！");
			reInfo.setErrorCode("10002");
			return reInfo;
		}
		if(productId.length == 0){
			reInfo.setErrorMessage("商品信息错误！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		if(activityId.length < 0){
			reInfo.setErrorMessage("活动信息错误！");
			reInfo.setErrorCode("10003");
			return reInfo;
		}
		int count=0;
		Order order = null;
		OrderProduct orderProduct=null;
		Schedule schedule = null;
		try {
			order = new Order();
			order.setUserId(Integer.parseInt(userId));
			order.setMerchantId(Integer.parseInt(merchantId));
			order.setDistributionMode(distributionMode);
			order.setUserMessage(userMessage);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			order.setArrivalTime(formatter.parse(arrivalTime));
			count=orderService.addOrder(order);//生成订单
			if(count<0){
				reInfo.setErrorMessage("生成订单失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			Integer orderId = order.getOrderId();
			//生成订单进度
			schedule = new Schedule();
			schedule.setOrderId(orderId);
			count = scheduleService.generateSchedule(schedule);
			if(count<0){
				reInfo.setErrorMessage("生成订单进度失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			//单独商品
			for (int i = 0; i < productId.length; i++) {
				
				for (int j = 0; j < Integer.parseInt(productNum[i]); j++) {
					orderProduct = new OrderProduct();
					orderProduct.setOrderId(orderId);				
					orderProduct.setActivityId(Integer.parseInt(activityId[i]));
					orderProduct.setProductId(Integer.parseInt(productId[i]));
					count=orderProductService.addOrderProduct(orderProduct);
					if(count<0){
						reInfo.setErrorMessage("生成商品单失败！");
						reInfo.setErrorCode("10006");
						return reInfo;
					}
				}
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("生成失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		rsInfo.setErrorMessage("生成订单成功！");
		rsInfo.setResult(order.getCode());
		return rsInfo;
	}

	//根据订单状态查询订单
	@RequestMapping(value="/user/getOrderListByStatus")
	public @ResponseBody
	Object getOrderListByStatus(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		if(userId == null || userId.equals("")){
			reInfo.setErrorMessage("获取用户信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(status == null || status.equals("")){
			reInfo.setErrorMessage("获取订单状态信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(pageNum == null || pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(pageSize == null || pageSize.equals("")){
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		List<OrderResult> orderList = new ArrayList<OrderResult>();
		List<OrderSort> orderResultList = new ArrayList<OrderSort>();
		if(status == 0){
			try {
				orderList = orderService.getUserOrderList(userId, pageNum, pageSize);
				OrderSort orderSort = null;
				HashSet<Integer> orderId = new HashSet<Integer>();
				for (OrderResult o : orderList) {
					orderId.add(o.getOrderResultId());
				}
				
				Iterator<Integer> iterator=orderId.iterator();
				while(iterator.hasNext()){
					orderSort = new OrderSort();
					orderSort.setOrderId(iterator.next());
					List<OrderResult> list = new ArrayList<OrderResult>();
					for (OrderResult order : orderList) {
						if(orderSort.getOrderId()==order.getOrderResultId()){
							list.add(order);
							orderSort.setOrderResultList(list);
						}
					}
					orderResultList.add(orderSort);
				}
				
				if(orderResultList.size()==0){
					reInfo.setErrorMessage("您还没有订单！");
					reInfo.setErrorCode("10006");
					return reInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("获取订单失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
		}else{
			try {
				orderList = orderService.getUserOrderListByStatus(userId,status, pageNum, pageSize);
				OrderSort orderSort = null;
				HashSet<Integer> orderId = new HashSet<Integer>();
				for (OrderResult o : orderList) {
					orderId.add(o.getOrderResultId());
				}
				
				Iterator<Integer> iterator=orderId.iterator();
				while(iterator.hasNext()){
					orderSort = new OrderSort();
					orderSort.setOrderId(iterator.next());
					List<OrderResult> list = new ArrayList<OrderResult>();
					for (OrderResult order : orderList) {
						if(orderSort.getOrderId()==order.getOrderResultId()){
							list.add(order);
							orderSort.setOrderResultList(list);
						}
					}
					orderResultList.add(orderSort);
				}
				if(orderResultList.size()==0){
					reInfo.setErrorMessage("此状态无订单！");
					reInfo.setErrorCode("10006");
					return reInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("获取订单失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
		}

		rsInfo.setErrorMessage("获取订单列表成功");
		rsInfo.setResult(orderResultList);
		return rsInfo;
	}
	//发货
	@RequestMapping(value="/order/sendProduct")
	public @ResponseBody
	Object sendProduct(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String orderId = request.getParameter("orderId");
		String[] opId = request.getParameterValues("opId");
		String[] batch = request.getParameterValues("batch");
		String[] qrcode = request.getParameterValues("qrcode");
		String distributionMode = request.getParameter("distributionMode");
		String expressCode = request.getParameter("expressCode");
		String expressExpenses = request.getParameter("expressExpenses");
		String expressCompany = request.getParameter("expressCompany");
		if(orderId==null||orderId.equals("")){
			reInfo.setErrorMessage("获取订单信息失败！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(opId.length==0){
			reInfo.setErrorMessage("获取订单商品失败！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(batch.length==0){
			reInfo.setErrorMessage("商品批次不能为空！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(qrcode.length==0){
			reInfo.setErrorMessage("商品二维码不能为空！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(expressCode==null||expressCode.equals("")){
			reInfo.setErrorMessage("物流单号不能为空！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(expressExpenses==null||expressExpenses.equals("")){
			reInfo.setErrorMessage("物流费不能为空！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(expressCompany==null||expressCompany.equals("")){
			reInfo.setErrorMessage("物流公司不能为空！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		if(distributionMode==null||distributionMode.equals("")){
			reInfo.setErrorMessage("配送方式不能为空！");
			reInfo.setErrorCode("10000");
			return reInfo;
		}
		OrderProduct orderProduct = new OrderProduct();
		Order order = new Order();
		int count = 0;
		for (int i = 0; i < opId.length; i++) {
			orderProduct.setOpId(Integer.parseInt(opId[i]));
			orderProduct.setBatch(batch[i]);
			orderProduct.setQrcode(qrcode[i]);
			count = orderProductService.modifyOrderProduct(orderProduct);
			if(count<0){
				reInfo.setErrorCode("10000");
				reInfo.setErrorMessage("绑定商品批号和二维码失败！");
				return reInfo;
			}
		}
		order.setOrderId(Integer.parseInt(orderId));
		order.setDistributionMode(distributionMode);
		order.setExpressCode(expressCode);
		order.setExpressExpenses(Double.parseDouble(expressExpenses));
		order.setExpressCompany(expressCompany);
		count = orderService.modifyOrderStatus(order);
		if(count<0){
			reInfo.setErrorCode("10000");
			reInfo.setErrorMessage("发货失败！");
			return reInfo;
		}
		rsInfo.setErrorMessage("发货成功！");
		rsInfo.setResult(count);
		return rsInfo;
	} 
	//修改订单状态
	@RequestMapping(value="/order/modifyOrderStatus")
	public @ResponseBody
	Object modifyOrderStatus(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String orderId = request.getParameter("orderId");
		String status = request.getParameter("status");
		if(orderId==null||orderId.equals("")){
			reInfo.setErrorMessage("获取订单信息失败！");
			reInfo.setErrorCode("10002");
			return reInfo;
		}
		if(status==null||status.equals("")){
			reInfo.setErrorMessage("获取订单状态失败！");
			reInfo.setErrorCode("10002");
			return reInfo;
		}
		Integer orderStatus = 0;
		if(status.equals("1")){
			orderStatus=1;
		}else if(status.equals("3")){
			orderStatus=3;
		}else if(status.equals("2")){
			orderStatus=2;
		}else if(status.equals("4")){
			orderStatus=4;
		}else if(status.equals("5")){
			orderStatus=5;
		}else if(status.equals("6")){
			orderStatus=6;
		}
		Order order = new Order();
		order.setOrderId(Integer.parseInt(orderId));
		order.setStatus(orderStatus);
		try {
			orderService.modifyOrderStatus(order);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("修改状态失败！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		rsInfo.setErrorMessage("修改状态成功！");
		rsInfo.setResult(order);
		return rsInfo;
	}
	//订单统计
	@RequestMapping(value="/order/getOrderCount")
	public @ResponseBody
	Object orderCount(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String merchantId = request.getParameter("merchantId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		List<OrderCount> orderList = new ArrayList<OrderCount>();
		OrderCount orderCount = new OrderCount();
		if(merchantId==null||merchantId.equals("")){
			reInfo.setErrorCode("10000");
			reInfo.setErrorMessage("获取商家失败！");
			return reInfo;
		}
		if(startTime==null||startTime.equals("")){
			reInfo.setErrorCode("10000");
			reInfo.setErrorMessage("开始时间错误！");
			return reInfo;
		}
		if(endTime==null||endTime.equals("")){
			reInfo.setErrorCode("10000");
			reInfo.setErrorMessage("结束时间错误！");
			return reInfo;
		}
		try {
			orderList = orderService.getOrderCount(Integer.parseInt(merchantId), startTime, endTime);
			if(orderList.size()==0){
				reInfo.setErrorCode("10000");
				reInfo.setErrorMessage("此时间段没有订单！");
				return reInfo;
			}else{
				int number=0;
				double price=0;
				for (OrderCount order : orderList) {
					if(order.getStatus()==1){
						orderCount.setStatus(order.getStatus());
						orderCount.setNumber(order.getNumber());
						orderCount.setPrice(order.getPrice());
						number+=order.getNumber();
						price+=order.getPrice();
					}
					if(order.getStatus()==2){
						orderCount.setStatus2(order.getStatus());
						orderCount.setNumber2(order.getNumber());
						orderCount.setPrice2(order.getPrice());
						number+=order.getNumber();
						price+=order.getPrice();
					}
					if(order.getStatus()==3){
						orderCount.setStatus3(order.getStatus());
						orderCount.setNumber3(order.getNumber());
						orderCount.setPrice3(order.getPrice());
						number+=order.getNumber();
						price+=order.getPrice();
					}
					if(order.getStatus()==4){
						orderCount.setStatus4(order.getStatus());
						orderCount.setNumber4(order.getNumber());
						orderCount.setPrice4(order.getPrice());
						number+=order.getNumber();
						price+=order.getPrice();
					}
					
				}
				orderCount.setNumberCount(number);
				orderCount.setPriceCount(price);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorCode("10000");
			reInfo.setErrorMessage("获取订单统计失败！");
			return reInfo;
		}
		rsInfo.setResult(orderCount);
		rsInfo.setErrorMessage("订单统计成功！");
		return rsInfo;
	}
	
	//获取订单申诉列表
	@RequestMapping(value="/order/getOrderAppealList")
	public @ResponseBody
	Object getOrderAppealList(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		Integer merchantId = Integer.parseInt(request.getParameter("merchantId"));
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		if(merchantId == null || merchantId.equals("")){
			reInfo.setErrorMessage("获取商家信息失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(pageNum == null || pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(pageSize == null || pageSize.equals("")){
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		List<OrderResult> orderList = new ArrayList<OrderResult>();
		List<OrderSort> orderResultList = new ArrayList<OrderSort>();
		try {
			orderList = orderService.getOrderAppealList(merchantId, pageNum, pageSize);
			OrderSort orderSort = null;
			HashSet<Integer> orderId = new HashSet<Integer>();
			for (OrderResult o : orderList) {
				orderId.add(o.getOrderResultId());
			}
			
			Iterator<Integer> iterator=orderId.iterator();
			while(iterator.hasNext()){
				orderSort = new OrderSort();
				orderSort.setOrderId(iterator.next());
				List<OrderResult> list = new ArrayList<OrderResult>();
				for (OrderResult order : orderList) {
					if(orderSort.getOrderId()==order.getOrderResultId()){
						list.add(order);
						orderSort.setOrderResultList(list);
					}
				}
				orderResultList.add(orderSort);
			}
			if(orderList.size()==0){
				reInfo.setErrorMessage("没有订单申诉！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取订单申诉列表失败！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}

		rsInfo.setErrorMessage("获取订单申诉列表成功！");
		rsInfo.setResult(orderResultList);
		return rsInfo;
	}
	/**
	 * 
	 * <p>
	 * Description: 根据Status获取order状态数据
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-12 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
	@RequestMapping(value = "/merchant/getOrderListByStatus")
	public @ResponseBody
	Object getOrderByStatus(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		Integer merchantId = Integer.parseInt(request.getParameter("merchantId"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        
		if (merchantId == null || merchantId.equals("")) {
			reInfo.setErrorMessage("商家ID不能为空！");
			rsInfo.setResult(null);
			return reInfo;
		}
		if (status == null || status.equals("")) {
			reInfo.setErrorMessage("订单状态不能为空！");
			rsInfo.setResult(null);
			return reInfo;
		}
		if(pageNum==null||pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		
		if(pageSize==null||pageSize.equals("")){
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		List<OrderResult> orderList = new ArrayList<OrderResult>();
		if (status == 0) {
			try {
				orderList = orderService.getMerchantOrderList(merchantId, pageNum, pageSize);
				
				if(orderList.size()==0){
					reInfo.setErrorMessage("您还没有订单！");
					reInfo.setErrorCode("10006");
					return reInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("获取订单失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
		} else {
			try {
				orderList = orderService.getMerchantOrderListByStatus(merchantId, status, pageNum, pageSize);
				
				if(orderList.size() == 0){
					reInfo.setErrorMessage("此状态无订单！");
					reInfo.setErrorCode("10006");
					return reInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("获取订单失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
		}

		rsInfo.setErrorMessage("获取订单列表成功");
		rsInfo.setResult(orderList);
		return rsInfo;
	}

	/**
	 * 
	 * <p>
	 * Description: 根据Date获取当日订单列表
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-13
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
	@RequestMapping(value = "/merchant/getOrderByCurrentDate")
	public @ResponseBody 
	Object getOrderByDate(HttpServletRequest request,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String currentDate = request.getParameter("currentDate");
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		Integer merchantId = Integer.parseInt(request.getParameter("merchantId"));
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		if(pageNum == null || pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(pageSize == null || pageSize.equals("")){
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(currentDate == null || currentDate.equals("")){
			reInfo.setErrorMessage("日期不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
			List<OrderResult> orderDateList = orderService.getBeforeOrderList(merchantId,currentDate,pageSize, pageNum);
			if (orderDateList.size() != 0) {
				rsInfo.setErrorMessage("统计订单成功！");
				rsInfo.setResult(orderDateList);
				
			} else {
				reInfo.setErrorMessage("获取当日订单列表失败！");
				reInfo.setErrorCode("100045");
				return reInfo;
			}
		return rsInfo;
	}

	/**
	 * 
	 * <p>
	 * Description: 根据Date获取今日order列表
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-13
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
	@RequestMapping(value = "/merchant/orderByDateList")
	public @ResponseBody 
	Object orderByDateList(HttpServletRequest request,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		Integer merchantId = Integer.parseInt(request.getParameter("merchantId"));
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		if(pageNum == null || pageNum.equals("")){
			reInfo.setErrorMessage("页码不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if(pageSize == null || pageSize.equals("")){
			reInfo.setErrorMessage("页大小不能为空！");
			reInfo.setErrorCode("10006");
			return reInfo;
		}
		if (merchantId != null) {
			List<Order> orderList = orderService.getOrderByOrderList(merchantId,pageSize,pageNum);
			if (orderList != null) {
				rsInfo.setErrorMessage("获取今日订单成功！");
				rsInfo.setResult(orderList);
			} else {
				reInfo.setErrorMessage("获取今日订单失败！");
				reInfo.setErrorCode("10003");
			}
		}else{
			reInfo.setErrorMessage("获取今日订单失败！");
			reInfo.setErrorCode("10003");
		}
		return rsInfo;
	}

	/**
	 * 
	 * <p>
	 * Description: 获取订单详情
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-14
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
	@RequestMapping(value = "/order/getOrderDetail")
	public @ResponseBody
	Object getOrderDetail(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		if(orderId==null){
			reInfo.setErrorMessage("status为空！");
			reInfo.setErrorCode("10003");
			return reInfo;
		} 
		OrderSort orderSort = null;
		try {
			List<OrderResult> orderList = orderService.selectByOrderDetails(orderId);
			HashSet<OrderSort> id = new HashSet<OrderSort>();
			for (OrderResult o : orderList) {
				orderSort = new OrderSort();
				orderSort.setOrderId(o.getOrderResultId());
				orderSort.setOrderCode(o.getOrderCode());
				orderSort.setUserName(o.getUserName());
				orderSort.setPhone(o.getMobile());
				orderSort.setRemark(o.getUserMessage());
				orderSort.setAddress(o.getAddress());
				id.add(orderSort);
			}
			
			for (int i = 0; i < id.size(); i++) {
				List<OrderResult> list = new ArrayList<OrderResult>();
				for (OrderResult order : orderList) {
					if(orderSort.getOrderId()==order.getOrderResultId()){
						list.add(order);
						orderSort.setOrderResultList(list);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("获取订单信息失败！");
			reInfo.setErrorCode("10003");
			return reInfo;	
		}
		
		if (orderSort==null) {
			reInfo.setErrorMessage("没有这个订单信息！");
			reInfo.setErrorCode("10003");
			return reInfo;	
		} else 
			rsInfo.setErrorMessage("订单信息获取成功！");
			rsInfo.setResult(orderSort);
		return rsInfo;
	}


	/**
	 * 
	 * <p>
	 * Description: 比较前一日的成交金额
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-13
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
/*	@RequestMapping(value = "/merchant/orderTurnover")
	public @ResponseBody 
	Object doOrderTurnover(HttpServletRequest request,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String  currentDate = request.getParameter("currentDate");
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		Integer merchantId = Integer.parseInt(request.getParameter("merchantId"));
		Double moneySum;
		Double  money = Double.valueOf(0);
		Double  yeMoney = Double.valueOf(0);
		Double consult=null;
		OrderResult OrderResult = new OrderResult();
		if (merchantId != null && currentDate != null) {
			//得到昨日订单数据（自己选择的日期）
			List<Order> orderList = orderService.selectByOrderTurnover(merchantId,currentDate);
			if (orderList != null&&orderList.size()!=0) {
				for (Order order : orderList) {
					//判断只需要已经成交的订单金额
					if (order.getStatus().equals(1)||order.getStatus().equals(3)||order.getStatus().equals(4)) {
						moneySum = order.getTurnover();
						money += moneySum;
					}
				}
			} else {
				rsInfo.setErrorMessage("获取昨日订单列表失败！");
			}
			String thyDate=DateUtil.convertDate(currentDate);
			//得到前日订单数据（自己选择的日期）
			List<Order> orderYeList = orderService.selectByOrderAnteayerTurnover(merchantId, thyDate);
			if (orderYeList != null) {
				for (Order order : orderYeList) {
					//判断只需要已经成交的订单金额
					if (order.getStatus().equals(1)||order.getStatus().equals(3)||order.getStatus().equals(4)) {
						moneySum = order.getTurnover();
						yeMoney += moneySum;
					}
				}
			} else {
				rsInfo.setErrorMessage("获取前一日订单列表失败！");
				rsInfo.setResult(null);
			}
			//判断金额
			if(money.doubleValue()!=0&&yeMoney.doubleValue()!=0){
				consult=money/yeMoney;
				OrderResult.setConsult(consult);
				OrderResult.setYeTurnoverAll(yeMoney);
				OrderResult.setTurnoverAll(money);
				rsInfo.setResult(OrderResult);
			}else if(yeMoney.doubleValue()==0){
				rsInfo.setErrorMessage("获取昨日订单金额为0不能比较！");
				rsInfo.setResult(null);
			}else if(yeMoney.doubleValue()==0){
				rsInfo.setErrorMessage("获取前一日订单金额为0不能比较！");
				rsInfo.setResult(null);
			}else{
				rsInfo.setErrorMessage("两个都为空！");
				rsInfo.setResult(null);
			}
		}else{
			reInfo.setErrorMessage("商户ID和日期不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		return rsInfo;
	}*/
	
	
	@RequestMapping(value = "/order/checkWine")
	public @ResponseBody Object getEvaluateListByProductId(
			HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String qrcode = request.getParameter("qrcode");

		if (userId == null || userId.equals("")) {
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}

		if (qrcode == null || qrcode.equals("")) {
			reInfo.setErrorMessage("二维码不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		OrderProductExt ope = null;
		try {
			ope = orderService.checkWine(qrcode);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("验酒失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if (ope == null) {
			reInfo.setErrorMessage("验酒失败！");
			reInfo.setErrorCode("10005");
			return reInfo;

		}
		
		if(ope.getUserId() != Integer.parseInt(userId)){
			ope.setOrderId(0);	
			ope.setOpId(0);
			ope.setUserId(0);
		}
		

		rsInfo.setErrorMessage("验酒成功！");
		rsInfo.setResult(ope);
		return rsInfo;
	}



}
