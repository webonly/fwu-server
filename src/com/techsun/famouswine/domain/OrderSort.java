package com.techsun.famouswine.domain;

import java.util.List;

public class OrderSort {
	private int orderId;
	private String userName;
	private String orderCode;
	private String phone;
	private String address;
	private String remark;
	private String distributionMode;
	private String expressCompany;
	private String expressCode;
	private Double expressExpenses;
	private List<OrderResult> orderResultList;
	
	
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDistributionMode() {
		return distributionMode;
	}
	public void setDistributionMode(String distributionMode) {
		this.distributionMode = distributionMode;
	}
	public String getExpressCompany() {
		return expressCompany;
	}
	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public Double getExpressExpenses() {
		return expressExpenses;
	}
	public void setExpressExpenses(Double expressExpenses) {
		this.expressExpenses = expressExpenses;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<OrderResult> getOrderResultList() {
		return orderResultList;
	}
	public void setOrderResultList(List<OrderResult> orderResultList) {
		this.orderResultList = orderResultList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	
}
