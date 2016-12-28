package com.techsun.famouswine.domain;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private Integer userId;

    private Integer merchantId;

    private Integer productId;

    private Double productScore;

    private String productContent;

    private Double merchantScore;

    private String merchantContent;

    private Double serveScore;

    private Boolean activated;

    private Boolean deleted;

    private Date updatedDate;

    private Date createdDate;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getProductScore() {
        return productScore;
    }

    public void setProductScore(Double productScore) {
        this.productScore = productScore;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent == null ? null : productContent.trim();
    }

    public Double getMerchantScore() {
        return merchantScore;
    }

    public void setMerchantScore(Double merchantScore) {
        this.merchantScore = merchantScore;
    }

    public String getMerchantContent() {
        return merchantContent;
    }

    public void setMerchantContent(String merchantContent) {
        this.merchantContent = merchantContent == null ? null : merchantContent.trim();
    }

    public Double getServeScore() {
        return serveScore;
    }

    public void setServeScore(Double serveScore) {
        this.serveScore = serveScore;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}