package com.techsun.famouswine.domain;

import java.util.Date;


public class CommonParam {

	private int commonParamId;
	private int userId;	//用户id
	private Integer startNum;//分页从第几条
	private Integer endNum;//分页每页显示几条
	private int type;//类型
	private String nickName;//用户昵称
	private int productId;//商品id
	private String productName;//商品名称
	private String productType;//商品类型		
	private int userType;
	private int dealType;
	private Double userLat;
	private Double userLon;
	private Double startPrice;
	private Double endPrice;
	private Integer distance;
	public int getDealType() {
		return dealType;
	}
	public void setDealType(int dealType) {
		this.dealType = dealType;
	}
	private int merchantId;//商家id
	private String merchantName;//商家名称
	private String content;//评价
	private int communityId;
	private Date createdDate;
	private int knowledgeId;
	private int status;  //状态
	private String createdMerchantDate;
	private String qrcode;
	private int orderId;

	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public String getCreatedMerchantDate() {
		return createdMerchantDate;
	}
	public void setCreatedMerchantDate(String createdMerchantDate) {
		this.createdMerchantDate = createdMerchantDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(int knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public int getCommonParamId() {
		return commonParamId;
	}
	public void setCommonParamId(int commonParamId) {
		this.commonParamId = commonParamId;
	}
	public int getCommunityId() {
		return communityId;
	}
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getStartNum() {
		return startNum;
	}
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	public Integer getEndNum() {
		return endNum;
	}
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public Double getUserLat() {
		return userLat;
	}
	public void setUserLat(Double userLat) {
		this.userLat = userLat;
	}
	public Double getUserLon() {
		return userLon;
	}
	public void setUserLon(Double userLon) {
		this.userLon = userLon;
	}
	
	public Double getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}
	public Double getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
	
}
