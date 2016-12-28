package com.techsun.famouswine.domain;

import java.util.Date;

public class Order {
    private Integer orderId;

    private Integer userId;

    private Integer merchantId;

    private String code;

	private Integer type;

    private Integer status;

    private Double turnover;

    private Integer couponId;
    private Double expressFee;

    private Date arrivalTime;

    private String userMessage;

    private String invoiceInfo;
    
	private String distributionMode;

    private String expressCompany;

    private String deliveryWay;

    private Double expressExpenses;

    private String expressCode;

    private String description;

    private Boolean activated;

    private Boolean deleted;

    private Date createdDate;

    private Date updatedDate;
    
    public String getExpressCompany() {
		return expressCompany;
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

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }
   

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

   
    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany == null ? null : expressCompany.trim();
    }
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public Double getTurnover() {
		return turnover;
	}

	public String getDescription() {
		return description;
	}

	public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}