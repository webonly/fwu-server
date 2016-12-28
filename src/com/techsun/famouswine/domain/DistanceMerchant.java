package com.techsun.famouswine.domain;

public class DistanceMerchant {
	private int merchantId;	
	private int productId;
	private String productName;
	private String productImageUrl;
	private String merchantImageUrl;
	private Double userLon;	//经度
	private Double userLat;	//纬度
	private int sales;
	private Double price;
	private Integer distance;
	private Double merchantScore;	//店铺评分
	private String merchantName;	//店铺名称
	private String address;	//店铺地址
	private Integer commentNum;	//评价数
	private Double startPrice;
	private Double endPrice;
	private Integer pageNum;
	private Integer pageSize;
	private String sort;
	

	
	
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getMerchantImageUrl() {
		return merchantImageUrl;
	}
	public void setMerchantImageUrl(String merchantImageUrl) {
		this.merchantImageUrl = merchantImageUrl;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Double getUserLon() {
		return userLon;
	}
	public void setUserLon(Double userLon) {
		this.userLon = userLon;
	}
	public Double getUserLat() {
		return userLat;
	}
	public void setUserLat(Double userLat) {
		this.userLat = userLat;
	}
	
	public Double getMerchantScore() {
		return merchantScore;
	}
	public void setMerchantScore(Double merchantScore) {
		this.merchantScore = merchantScore;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
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
	
	
}
