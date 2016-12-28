package com.techsun.famouswine.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Schedule;
import com.techsun.famouswine.service.ScheduleService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class ScheduleController {
	private ScheduleService scheduleService;

	public ScheduleService getScheduleService() {
		return scheduleService;
	}
	@Autowired
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	
	
	//根据订单号查询进度
	@RequestMapping(value="/schedule/getScheduleByCode")
	public @ResponseBody
	Object getScheduleByCode(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String orderId = request.getParameter("orderId");
		if(orderId==null||orderId.equals("")){
			reInfo.setErrorMessage("订单信息错误！");
			reInfo.setErrorCode("10008");
			return reInfo;
		}
		Schedule schedule = null;
		try {
			schedule=scheduleService.getScheduleByCode(Integer.parseInt(orderId));
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("订单信息错误！");
			reInfo.setErrorCode("10008");
			return reInfo;
		}
		rsInfo.setErrorMessage("获取订单进度");
		rsInfo.setResult(schedule);
		return rsInfo;
	}

}
