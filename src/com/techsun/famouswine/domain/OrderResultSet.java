package com.techsun.famouswine.domain;


public class OrderResultSet {
	private Double receipPrice; // 未收货金额
	private Double payingPrice; // 未付款金额
	private Double refundPrice; // 退货金额
	private Double evaluatePrice; // 评价金额
	private Double shippingPrice; // 未发货金额
	private Integer noReceiptcount; // 未收货
	private Integer noPayingcount; // 未付款
	private Integer refundcount; // 退货
	private Integer evaluatecount; // 评价
	private Integer noShippingcount; // 未发货
	private Double turnover; // 成交订单金额
	private Integer couponId;


	private Integer status; // 订单状态
	private Double yeTurnoverAll; //昨日成交总额
	private Double turnoverAll; //昨日成交总额
	private Double consult ;    //商
	private Integer merchantId;      //id
	private String  createdDate;      //日期
	private String imageUrl;
	private Integer number ;
	private Double price ;
	
	
	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Double getConsult() {
		return consult;
	}

	public void setConsult(Double consult) {
		this.consult = consult;
	}

	public Double getYeTurnoverAll() {
		return yeTurnoverAll;
	}

	public void setYeTurnoverAll(Double yeTurnoverAll) {
		this.yeTurnoverAll = yeTurnoverAll;
	}

	public Double getTurnoverAll() {
		return turnoverAll;
	}

	public void setTurnoverAll(Double turnoverAll) {
		this.turnoverAll = turnoverAll;
	}

	public Double getReceipPrice() {
		return receipPrice;
	}

	public void setReceipPrice(Double receipPrice) {
		this.receipPrice = receipPrice;
	}

	public Double getPayingPrice() {
		return payingPrice;
	}

	public void setPayingPrice(Double payingPrice) {
		this.payingPrice = payingPrice;
	}

	public Double getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(Double refundPrice) {
		this.refundPrice = refundPrice;
	}

	public Double getEvaluatePrice() {
		return evaluatePrice;
	}

	public void setEvaluatePrice(Double evaluatePrice) {
		this.evaluatePrice = evaluatePrice;
	}

	public Double getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(Double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public Integer getNoReceiptcount() {
		return noReceiptcount;
	}

	public void setNoReceiptcount(Integer noReceiptcount) {
		this.noReceiptcount = noReceiptcount;
	}

	public Integer getNoPayingcount() {
		return noPayingcount;
	}

	public void setNoPayingcount(Integer noPayingcount) {
		this.noPayingcount = noPayingcount;
	}

	public Integer getRefundcount() {
		return refundcount;
	}

	public void setRefundcount(Integer refundcount) {
		this.refundcount = refundcount;
	}

	public Integer getEvaluatecount() {
		return evaluatecount;
	}

	public void setEvaluatecount(Integer evaluatecount) {
		this.evaluatecount = evaluatecount;
	}

	public Integer getNoShippingcount() {
		return noShippingcount;
	}

	public void setNoShippingcount(Integer noShippingcount) {
		this.noShippingcount = noShippingcount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}
		
        
		
        
}
