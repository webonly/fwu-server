package com.techsun.famouswine.domain;

import java.util.Date;

public class Evaluate {
    private Integer orderId;

    private Integer userId;

    private Integer productAgreement;

    private Integer merchantService;

    private Integer expressService;

    private String content;

    private String reply;

    private Boolean activated;

    private Boolean deleted;

    private Date createdDate;

    private Date updatedDate;

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

    public Integer getProductAgreement() {
        return productAgreement;
    }

    public void setProductAgreement(Integer productAgreement) {
        this.productAgreement = productAgreement;
    }

    public Integer getMerchantService() {
        return merchantService;
    }

    public void setMerchantService(Integer merchantService) {
        this.merchantService = merchantService;
    }

    public Integer getExpressService() {
        return expressService;
    }

    public void setExpressService(Integer expressService) {
        this.expressService = expressService;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
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

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}