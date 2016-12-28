package com.techsun.famouswine.domain;

public class BrowseResultSet {
	private Integer status; 					// 订单状态
	private Double turnoverAll;      		//昨日成交总额
	private Double yeTurnoverAll;     		 //前日成交总额
	private Double transactionConsult ;   	 //昨前金额商
	private Integer visits;              		//昨日访问量
	private Integer yeVisits;              		//前日访问量
	private int visitConsult ;    				//昨前访问商
    private int knockdown;         				//昨日成交量
    private int yeKnockdown;        		 //前日成交量
	private int orderConsult ;    			//昨前订单成交商
	private Integer merchantId;          	//id
	private String  createdDate;        	 //日期
	private Double turnover ;           //订单金额
	
	public Double getTurnover() {
		return turnover;
	}
	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getTurnoverAll() {
		return turnoverAll;
	}
	public void setTurnoverAll(Double turnoverAll) {
		this.turnoverAll = turnoverAll;
	}
	public Double getYeTurnoverAll() {
		return yeTurnoverAll;
	}
	public void setYeTurnoverAll(Double yeTurnoverAll) {
		this.yeTurnoverAll = yeTurnoverAll;
	}
	public Double getTransactionConsult() {
		return transactionConsult;
	}
	public void setTransactionConsult(Double transactionConsult) {
		this.transactionConsult = transactionConsult;
	}
	public Integer getVisits() {
		return visits;
	}
	public void setVisits(Integer visits) {
		this.visits = visits;
	}
	public Integer getYeVisits() {
		return yeVisits;
	}
	public void setYeVisits(Integer yeVisits) {
		this.yeVisits = yeVisits;
	}
	public int getVisitConsult() {
		return visitConsult;
	}
	public void setVisitConsult(int visitConsult) {
		this.visitConsult = visitConsult;
	}
	public int getKnockdown() {
		return knockdown;
	}
	public void setKnockdown(int knockdown) {
		this.knockdown = knockdown;
	}
	public int getYeKnockdown() {
		return yeKnockdown;
	}
	public void setYeKnockdown(int yeKnockdown) {
		this.yeKnockdown = yeKnockdown;
	}
	public int getOrderConsult() {
		return orderConsult;
	}
	public void setOrderConsult(int orderConsult) {
		this.orderConsult = orderConsult;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
