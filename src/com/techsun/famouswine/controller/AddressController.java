package com.techsun.famouswine.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techsun.famouswine.domain.Address;
import com.techsun.famouswine.service.AddressService;
import com.techsun.famouswine.util.ResultErrorInfo;
import com.techsun.famouswine.util.ResultSuccessInfo;

@Controller
public class AddressController {
	private AddressService addressService;

	public AddressService getAddressService() {
		return addressService;
	}
	@Autowired
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
	
	
	
	@RequestMapping(value="/address/addAddress")
	public @ResponseBody
	Object getAddress(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response){
		ResultSuccessInfo rsInfo = new ResultSuccessInfo();
		ResultErrorInfo reInfo = new ResultErrorInfo();
		String userId = request.getParameter("userId");
		String addressee = request.getParameter("addressee");
		String addresseePhone = request.getParameter("addresseePhone");
		String address = request.getParameter("address");
		if(userId==null||userId.equals("")){
			reInfo.setErrorMessage("用户ID不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		
		if(address==null||address.equals("")){
			reInfo.setErrorMessage("地址不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(addressee==null||addressee.equals("")){
			reInfo.setErrorMessage("收件人不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(addresseePhone==null||addresseePhone.equals("")){
			reInfo.setErrorMessage("收件人电话不能为空！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		Address addr= new Address();
		addr.setUserId(Integer.parseInt(userId));
		addr.setAddress(address);
		addr.setAddressee(addressee);
		addr.setAddresseePhone(addresseePhone);
		int count = 0;
		try {
			count = addressService.addAddress(addr);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
		}
		if(count > 0){
			rsInfo.setErrorMessage("添加地址成功！");
			rsInfo.setResult(count);			
		}else{
			reInfo.setErrorMessage("添加地址失败！");
			reInfo.setErrorCode("10005");
			return reInfo;
			
		}
		

		return rsInfo;
	}
	
	
	
	//根据地址获取经度纬度
		@RequestMapping(value="/deleteAddress")
		public @ResponseBody
		Object deleteAddress(HttpServletRequest request,HttpSession session, 
				HttpServletResponse response){
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();
			String addressId = request.getParameter("addressId");
			
			if(addressId==null||addressId.equals("")){
				reInfo.setErrorMessage("地址不能为空！");
				reInfo.setErrorCode("10005");
				return reInfo;
			}
			int count = 0;
			try {
				count = addressService.deleteAddress(Integer.parseInt(addressId));
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("添加地址失败！");
				reInfo.setErrorCode("10005");
				return reInfo;
			}
			if(count > 0){
				rsInfo.setErrorMessage("添加地址成功！");
				rsInfo.setResult(count);			
			}else{
				reInfo.setErrorMessage("添加地址失败！");
				reInfo.setErrorCode("10005");
				return reInfo;
				
			}
			

			return rsInfo;
		}
		//设置默认地址
		@RequestMapping(value="/address/setDefaultAddress")
		public @ResponseBody
		Object setDefaultAccount(HttpServletRequest request,HttpSession session, 
				HttpServletResponse response){
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();
			String userId = request.getParameter("userId");
			String addressId = request.getParameter("addressId");
			if(userId==null||userId.equals("")){
				reInfo.setErrorMessage("获取用户信息失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			if(addressId==null||addressId.equals("")){
				reInfo.setErrorMessage("获取地址信息失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			
			
			int count = 0;
			try {
				count=addressService.setDefaultAddress(Integer.parseInt(userId),Integer.parseInt(addressId));
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("设置默认地址失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			rsInfo.setResult(count);
			rsInfo.setErrorMessage("设置默认地址成功！");
			return rsInfo;
		}
		//获取默认地址
		@RequestMapping(value="/address/getDefaultAddress")
		public @ResponseBody
		Object getDefaultAccount(HttpServletRequest request,HttpSession session, 
				HttpServletResponse response){
			ResultSuccessInfo rsInfo = new ResultSuccessInfo();
			ResultErrorInfo reInfo = new ResultErrorInfo();
			String userId=request.getParameter("userId");
			String addressId = request.getParameter("addressId");
			if(userId==null||userId.equals("")){
				reInfo.setErrorMessage("获取用户信息失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			
			if(addressId==null||addressId.equals("")){
				reInfo.setErrorMessage("获取地址失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			Address address = null;
			try {
				address=addressService.getDefaultAddress(Integer.parseInt(userId), Integer.parseInt(addressId));
				if(address ==null){
					reInfo.setErrorMessage("用户未设置默认地址！");
					reInfo.setErrorCode("10006");
					return reInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				reInfo.setErrorMessage("获取默认地址失败！");
				reInfo.setErrorCode("10006");
				return reInfo;
			}
			rsInfo.setResult(address);
			rsInfo.setErrorMessage("获取默认地址成功！");
			return rsInfo;
		}
		
		
}
