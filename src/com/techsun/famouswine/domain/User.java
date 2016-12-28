package com.techsun.famouswine.domain;

import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    private String password;

    private String hwUsername;

    private String hwPassword;

    private String icon;

    private String nickName;

    private String realName;

    private Integer drinkAge;

    private Float drinkingCapacity;

    private String level;

    private String hobby;

    private Integer integral;

    private Integer accumulatedIntegral;

    private Integer invitedCode;

    private String sign;

    private String gender;

    private Date birthday;

    private String mobile;

    private String email;

    private String address;

    private Double latitude;

    private Double longitude;

    private String city;

    private String profession;

    private String company;

    private Boolean activated;

    private Boolean deleted;

    private Date updatedDate;

    private Date createdDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getDrinkAge() {
        return drinkAge;
    }

    public void setDrinkAge(Integer drinkAge) {
        this.drinkAge = drinkAge;
    }

    public Float getDrinkingCapacity() {
        return drinkingCapacity;
    }

    public void setDrinkingCapacity(Float drinkingCapacity) {
        this.drinkingCapacity = drinkingCapacity;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getAccumulatedIntegral() {
        return accumulatedIntegral;
    }

    public void setAccumulatedIntegral(Integer accumulatedIntegral) {
        this.accumulatedIntegral = accumulatedIntegral;
    }

    public Integer getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(Integer invitedCode) {
        this.invitedCode = invitedCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
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