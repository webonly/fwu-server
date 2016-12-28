package com.techsun.famouswine.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.BrowseCount;
import com.techsun.famouswine.domain.BrowseResultSet;
import com.techsun.famouswine.domain.OrderCount;
import com.techsun.famouswine.service.BrowseService;
import com.techsun.famouswine.service.MerchantService;
import com.techsun.famouswine.service.OrderService;
import com.techsun.famouswine.util.DateUtil;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class BrowseController {

	private BrowseService browseService;
	private MerchantService merchantService;
	private OrderService orderService;

	public OrderService getOrderService() {
		return orderService;
	}

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public BrowseService getBrowseService() {
		return browseService;
	}

	@Autowired
	public void setBrowseService(BrowseService browseService) {
		this.browseService = browseService;
	}

	public MerchantService getMerchantService() {
		return merchantService;
	}

	@Autowired
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	/**
	 * 
	 * <p>
	 * Description:统计流量
	 * </p>
	 * 
	 * @author LW
	 * @date 2015-11-16
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version 1.0
	 */
/*	@RequestMapping(value = "/merchant/getBrowseCount")
	public @ResponseBody Object getBrowseCount(HttpServletRequest request,
			HttpSession session) {
		System.out.println("111111111");
		String currentDate = request.getParameter("currentDate");
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		Integer merchantId = Integer.parseInt(request.getParameter("merchantId"));
		Double moneySum;
		Double turnoverAll = Double.valueOf(0); // 昨日成交总额
		Double yeTurnoverAll = Double.valueOf(0); // 前日成交总额
		Double transactionConsult = Double.valueOf(0); // 昨前金额商
		int visits = 0; // 昨日访问量
		int yeVisits = 0; // 前日访问量
		int knockdown = 0; // 昨日成交量
		int yeKnockdown = 0; // 前日成交量
		BrowseResultSet browseResultSet = new BrowseResultSet();
		if (merchantId != null && currentDate != null) {
			
			List<BrowseResultSet> BrowseList = browseService.selectBrowseCount(merchantId, currentDate);
			List<Order> orderResultSet = orderService.selectByOrderTurnover(merchantId, currentDate);

			if (BrowseList.size() != 0) {
				visits = BrowseList.size();
			} else {
				visits = 0;
			}

			// 待发货2,待收货3,待评价4
			if (orderResultSet.size() != 0) {
				for (Order order : orderResultSet) {
					if (order.getStatus().equals(2)|| order.getStatus().equals(3)|| order.getStatus().equals(4)) {
						knockdown++;
						moneySum = order.getTurnover();
						turnoverAll += moneySum;
					}
				}
			} else {
				knockdown = 0;
				turnoverAll = Double.parseDouble("0");
			}

			String thyDate = DateUtil.convertDate(currentDate);
			List<BrowseResultSet> browseYeList = browseService.selectYeBrowseCount(merchantId, thyDate);
			List<Order> orderYeResultSet = orderService.selectByOrderTurnover(merchantId, thyDate);

			if (browseYeList.size() != 0) {
				yeVisits = browseYeList.size();
			} else {
				yeVisits = 0;
			}

			if (orderYeResultSet.size() != 0) {
				for (Order orderYe : orderYeResultSet) {
					// 待发货2,待收货3,待评价4
					if (orderYe.getStatus().equals(2)|| orderYe.getStatus().equals(3)|| orderYe.getStatus().equals(4)) {
						yeKnockdown++;
						moneySum = orderYe.getTurnover();
						yeTurnoverAll += moneySum;
					}
				}
			} else {
				yeKnockdown = 0;
				yeTurnoverAll = Double.parseDouble("0");
			}
            
			//成交量
			browseResultSet.setTurnoverAll(turnoverAll);
			browseResultSet.setYeTurnoverAll(yeTurnoverAll);
			if (yeTurnoverAll == 0 || turnoverAll == 0) {
				browseResultSet.setTransactionConsult(Double.parseDouble("0"));
			} else {
				transactionConsult = yeTurnoverAll / turnoverAll;
				browseResultSet.setTransactionConsult(transactionConsult);
			}
            
			//访问量
			browseResultSet.setVisits(visits);
			browseResultSet.setYeVisits(yeVisits);
			if (visits == 0 || yeVisits == 0) {
				browseResultSet.setVisitConsult(0);
			} else {
				browseResultSet.setVisitConsult(visits / yeVisits);
			}
			
			//成交金额
			browseResultSet.setKnockdown(knockdown);
			browseResultSet.setYeKnockdown(yeKnockdown);
			if (knockdown == 0 || yeKnockdown == 0) {
				browseResultSet.setOrderConsult(0);
			} else {
				browseResultSet.setOrderConsult(knockdown / yeKnockdown);
			}
			
			rsInfo.setResult(browseResultSet);
		} else {
			reInfo.setErrorMessage("商户ID和日期不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		return rsInfo;
	}*/
	
	
	
	@RequestMapping(value = "/merchant/getBrowseCount")
	public @ResponseBody Object getBrowseCount(HttpServletRequest request,
			HttpSession session) {
		String currentDate = request.getParameter("currentDate");
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		Integer merchantId = Integer.parseInt(request.getParameter("merchantId"));
		Double turnoverAll = Double.valueOf(0); // 昨日成交总额
		Double yeTurnoverAll = Double.valueOf(0); // 前日成交总额
		Double transactionConsult = Double.valueOf(0); // 昨前金额商
		int visits = 0; // 昨日访问量
		int yeVisits = 0; // 前日访问量
		int knockdown = 0; // 昨日成交量
		int yeKnockdown = 0; // 前日成交量
		
		String yesterday = DateUtil.convertDate(currentDate);
		BrowseResultSet browseResultSet = new BrowseResultSet();
		if (merchantId != null && currentDate != null) {
			
			BrowseCount browse = browseService.selectBrowseCount(merchantId, yesterday);
			OrderCount orderCount = orderService.selectByOrderTurnover(merchantId, yesterday);
			
			String thyDate = DateUtil.convertDate(yesterday);
			BrowseCount browseYeList = browseService.selectBrowseCount(merchantId, thyDate);
			OrderCount orderYeCount = orderService.selectByOrderTurnover(merchantId, thyDate);
            
			if(orderCount.getTurnoverSum() == null){
				orderCount.setTurnoverSum(0.0);
			}
			
			if(orderYeCount.getTurnoverSum() == null){
				orderYeCount.setTurnoverSum(0.0);
			}
			

			
			browseResultSet.setVisits(browse.getBrowseCount());
			browseResultSet.setYeVisits(browseYeList.getBrowseCount());

			
			if (browse.getBrowseCount() == 0 || browseYeList.getBrowseCount() == 0) {
				browseResultSet.setVisitConsult(0);
			} else {
				browseResultSet.setVisitConsult(browse.getBrowseCount() / browseYeList.getBrowseCount());
			}
			
			browseResultSet.setKnockdown(orderCount.getOrderCount());
			browseResultSet.setYeKnockdown(orderYeCount.getOrderCount());
			
			if (orderCount.getOrderCount() == 0 || orderYeCount.getOrderCount() == 0) {
				browseResultSet.setOrderConsult(0);
			} else {
				browseResultSet.setOrderConsult(orderCount.getOrderCount() / orderYeCount.getOrderCount());
			}
			
			
			//成交金额
			browseResultSet.setTurnover(orderCount.getTurnoverSum());
			
			browseResultSet.setYeTurnoverAll(orderYeCount.getTurnoverSum());
			if (orderCount.getTurnoverSum() == 0 || orderYeCount.getTurnoverSum() == 0) {
				browseResultSet.setTransactionConsult(0.0);
			} else {
				browseResultSet.setTransactionConsult(orderYeCount.getTurnoverSum()/orderYeCount.getTurnoverSum());
			}
			
			rsInfo.setResult(browseResultSet);
		} else {
			reInfo.setErrorMessage("商户ID和日期不能为空！");
			reInfo.setErrorCode("10001");
			return reInfo;
		}
		return rsInfo;
	}
	
	

	
}
