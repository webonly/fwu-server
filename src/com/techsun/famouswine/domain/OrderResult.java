package com.techsun.famouswine.domain;

import java.util.Date;

public class OrderResult {
	private int orderResultId;
	private Integer userId;//用户id
	private String userName;//用户名称
	private String orderCode;//订单号
	private Integer productId;//商品id
	private String productName;//商品名称
	private String productCapacity;//商品的容量
	private String merchantName;
	private String productUrl;//商品的图片
	private Double productPrice;//商品的价格
	private Date createdDate;
	private Date arrivalTime;//送到时间
	private String distribution;//配送方式
	private Integer productNum;
	private Integer status;//订单的状态
	private String content;//留言
	private String mobile;
	private String address;
	private String startTime;
	private String endTime;
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
	private Double yeTurnoverAll; //昨日成交总额
	private Double turnoverAll; //昨日成交总额
	private Double consult ;    //商
	private Integer merchantId;      //id
	private String imageUrl;
	private Integer number ;
	private Double price ;
	private String createdMerchantDate;
	private String code;
	private Integer amount ;
	private Double purchasePrice ;
	private String name;
	private Integer type;
	private Double expressFee;
	private String userMessage;
	private String invoiceInfo;
    private String distributionMode;
    private String deliveryWay;
    private Double expressExpenses;
    private String expressCode;
    private String description;
    private Boolean activated;
    private Boolean deleted;
    private Date updatedDate;
    private Integer appealType;
    private String appealContent;
    private String appealResult;
	
	
	public Integer getAppealType() {
		return appealType;
	}

	public void setAppealType(Integer appealType) {
		this.appealType = appealType;
	}

	public String getAppealContent() {
		return appealContent;
	}

	public void setAppealContent(String appealContent) {
		this.appealContent = appealContent;
	}

	public String getAppealResult() {
		return appealResult;
	}

	public void setAppealResult(String appealResult) {
		this.appealResult = appealResult;
	}

	public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public Double getPurchasePrice() {
			return purchasePrice;
		}

		public void setPurchasePrice(Double purchasePrice) {
			this.purchasePrice = purchasePrice;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Double getExpressFee() {
			return expressFee;
		}

		public void setExpressFee(Double expressFee) {
			this.expressFee = expressFee;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public void setUserMessage(String userMessage) {
			this.userMessage = userMessage;
		}

		public String getInvoiceInfo() {
			return invoiceInfo;
		}

		public void setInvoiceInfo(String invoiceInfo) {
			this.invoiceInfo = invoiceInfo;
		}

		public String getDistributionMode() {
			return distributionMode;
		}

		public void setDistributionMode(String distributionMode) {
			this.distributionMode = distributionMode;
		}

		public String getDeliveryWay() {
			return deliveryWay;
		}

		public void setDeliveryWay(String deliveryWay) {
			this.deliveryWay = deliveryWay;
		}

		public Double getExpressExpenses() {
			return expressExpenses;
		}

		public void setExpressExpenses(Double expressExpenses) {
			this.expressExpenses = expressExpenses;
		}

		public String getExpressCode() {
			return expressCode;
		}

		public void setExpressCode(String expressCode) {
			this.expressCode = expressCode;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Boolean getActivated() {
			return activated;
		}

		public void setActivated(Boolean activated) {
			this.activated = activated;
		}

		public Boolean getDeleted() {
			return deleted;
		}

		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}

	public String getCreatedMerchantDate() {
		return createdMerchantDate;
	}

	public void setCreatedMerchantDate(String createdMerchantDate) {
		this.createdMerchantDate = createdMerchantDate;
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

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
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

	public Double getConsult() {
		return consult;
	}

	public void setConsult(Double consult) {
		this.consult = consult;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getOrderResultId() {
		return orderResultId;
	}
	public void setOrderResultId(int orderResultId) {
		this.orderResultId = orderResultId;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductCapacity() {
		return productCapacity;
	}
	public void setProductCapacity(String productCapacity) {
		this.productCapacity = productCapacity;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public String getDistribution() {
		return distribution;
	}
	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}
	public Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
