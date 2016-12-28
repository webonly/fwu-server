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

import com.techsun.famouswine.domain.Advertisement;
import com.techsun.famouswine.domain.AdvertisementSort;
import com.techsun.famouswine.service.AdvertisementService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class AdvertisementController {
	private AdvertisementService advertisementService;

	public AdvertisementService getAdvertisementService() {
		return advertisementService;
	}
	@Autowired
	public void setAdvertisementService(AdvertisementService advertisementService) {
		this.advertisementService = advertisementService;
	}
	
	
	
	
	
	
	
	
		//根据地址获取经度纬度
		@RequestMapping(value="/getAdvertisementListByType")
		public @ResponseBody
		Object getAdvertisementListByProductId(HttpServletRequest request,HttpSession session, 
				HttpServletResponse response){
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();
			String type = request.getParameter("type");
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			
			if(type==null||type.equals("")){
				reInfo.setErrorMessage("广告类型不能为空！");
				reInfo.setErrorCode("10005");
				return reInfo;
			}
			
			if(pageNum==null||pageNum.equals("")){
				reInfo.setErrorMessage("页码不能为空！");
				reInfo.setErrorCode("10005");
				return reInfo;
			}
			if(pageSize==null||pageSize.equals("")){
				reInfo.setErrorMessage("页大小不能为空！");
				reInfo.setErrorCode("10005");
				return reInfo;
			}
			List<Advertisement> advertisementList = null;
			try {
				advertisementList = advertisementService.getAdvertisementListByType(Integer.parseInt(type), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("添加地址失败！");
				reInfo.setErrorCode("10005");
				return reInfo;
			}
			if(advertisementList == null){
				reInfo.setErrorMessage("添加地址失败！");
				reInfo.setErrorCode("10005");
				return reInfo;
				
			}
			
			rsInfo.setErrorMessage("添加地址成功！");
			rsInfo.setResult(advertisementList);	
			return rsInfo;
		}
		
		
		//根据地址获取经度纬度
				@RequestMapping(value="/getHomeAdvertisementList")
				public @ResponseBody
				Object getHomeAdvertisementList(HttpServletRequest request,HttpSession session, 
						HttpServletResponse response){
					ResultSuccessInfo rsInfo = new ResultSuccessInfo();
					ResultErrorInfo reInfo = new ResultErrorInfo();
					
					List<Advertisement> advertisementList = null;
					try {
						advertisementList = advertisementService.getHomeAdvertisementList();
					} catch (Exception e) {
						e.printStackTrace();
						reInfo.setErrorMessage("添加地址失败！");
						reInfo.setErrorCode("10005");
						return reInfo;
					}
					if(advertisementList == null){
						reInfo.setErrorMessage("添加地址失败！");
						reInfo.setErrorCode("10005");
						return reInfo;
						
					}
					
					List<AdvertisementSort> asList = new ArrayList<AdvertisementSort>();					
					
						
					for(int i = 1; i<= 5; i++){
						AdvertisementSort as = new AdvertisementSort();
						List<Advertisement> adList = new ArrayList<Advertisement>();
						as.setType(i);
						for(Advertisement ad: advertisementList){	
						if(ad.getType() == i){
							
							adList.add(ad);
							
						}
						}
						
						
						as.setAdvertisementList(adList);
						asList.add(as);
						
					}
					
					
					
					rsInfo.setErrorMessage("添加地址成功！");
					rsInfo.setResult(asList);	
					return rsInfo;
				}
		
		
}
