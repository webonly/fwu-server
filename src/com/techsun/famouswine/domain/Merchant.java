package com.techsun.famouswine.domain;

import java.util.Date;

public class Merchant {
    private Integer merchantId;

    private String merchantName;

    private String userName;

    private String password;

    private String hwUsername;

    private String hwPassword;

    private String icon;

    private String manager;

    private Integer age;

    private String address;

    private String phone;

    private String mobile;

    private String certificate;

    private String identificationCard;

    private String identificationBehind;

    private String identificationHold;

    private String identificationFront;

    private String businessLicence;

    private Integer integral;

    private Double latitude;

    private Double longitude;

    private String description;

    private Boolean activated;

    private Boolean deleted;

    private Date createdDate;

    private Date updatedDate;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getHwUsername() {
        return hwUsername;
    }

    public void setHwUsername(String hwUsername) {
        this.hwUsername = hwUsername == null ? null : hwUsername.trim();
    }

    public String getHwPassword() {
        return hwPassword;
    }

    public void setHwPassword(String hwPassword) {
        this.hwPassword = hwPassword == null ? null : hwPassword.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard == null ? null : identificationCard.trim();
    }

    public String getIdentificationBehind() {
        return identificationBehind;
    }

    public void setIdentificationBehind(String identificationBehind) {
        this.identificationBehind = identificationBehind == null ? null : identificationBehind.trim();
    }

    public String getIdentificationHold() {
        return identificationHold;
    }

    public void setIdentificationHold(String identificationHold) {
        this.identificationHold = identificationHold == null ? null : identificationHold.trim();
    }

    public String getIdentificationFront() {
        return identificationFront;
    }

    public void setIdentificationFront(String identificationFront) {
        this.identificationFront = identificationFront == null ? null : identificationFront.trim();
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence == null ? null : businessLicence.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
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

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}