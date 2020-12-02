package domain;

import java.util.Map;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/11/30 10:38
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
public class BaseUser extends Entity {
    private static final long serialVersionUID = 714176381926968855L;
    private String openId;
    private String userName;
    private String avatar;
    private String avatarUrl;
    private String realName;
    private String password;
    private String salt;
    private String smsVerifyCode;
    private String mailVerifyCode;
    private String imgVerifyCode;
    private int state;
    private String identityCode;
    private int gender;
    private String mobile;
    private int interCode;
    private String email;
    private String summary;
    private int profession;
    private String location1;
    private String location2;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private int registType;
    private int registSource;
    private String fp;
    private String appId;
    private String appName;
    private String registTypeLabel;
    private String registSourceLabel;
    private int userType;
    private String userTypeLabel;
    private String systemUserId;
    private String bindWXUqId;
    private String bindQQUqId;
    private String bindWBUqId;
    private String bindAppleUqId;
    private String requestIp;
    private int appChannel;
    private String resourceCat;
    private Integer isStaff;

    public BaseUser() {
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSmsVerifyCode() {
        return this.smsVerifyCode;
    }

    public void setSmsVerifyCode(String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }

    public String getMailVerifyCode() {
        return this.mailVerifyCode;
    }

    public void setMailVerifyCode(String mailVerifyCode) {
        this.mailVerifyCode = mailVerifyCode;
    }

    public String getImgVerifyCode() {
        return this.imgVerifyCode;
    }

    public void setImgVerifyCode(String imgVerifyCode) {
        this.imgVerifyCode = imgVerifyCode;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getIdentityCode() {
        return this.identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRegistType() {
        return this.registType;
    }

    public void setRegistType(int registType) {
        this.registType = registType;
    }

    public int getRegistSource() {
        return this.registSource;
    }

    public void setRegistSource(int registSource) {
        this.registSource = registSource;
    }

    public String getFp() {
        return this.fp;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    public void setRegistTypeLabel(String registTypeLabel) {
        this.registTypeLabel = registTypeLabel;
    }

    public void setRegistSourceLabel(String registSourceLabel) {
        this.registSourceLabel = registSourceLabel;
    }


    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getSystemUserId() {
        return this.systemUserId;
    }

    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getBindWXUqId() {
        return this.bindWXUqId;
    }

    public void setBindWXUqId(String bindWXUqId) {
        this.bindWXUqId = bindWXUqId;
    }

    public String getBindQQUqId() {
        return this.bindQQUqId;
    }

    public void setBindQQUqId(String bindQQUqId) {
        this.bindQQUqId = bindQQUqId;
    }

    public String getBindWBUqId() {
        return this.bindWBUqId;
    }

    public void setBindWBUqId(String bindWBUqId) {
        this.bindWBUqId = bindWBUqId;
    }

    public String getBindAppleUqId() {
        return this.bindAppleUqId;
    }

    public void setBindAppleUqId(String bindAppleUqId) {
        this.bindAppleUqId = bindAppleUqId;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getProfession() {
        return this.profession;
    }

    public void setProfession(int profession) {
        this.profession = profession;
    }

    public String getLocation1() {
        return this.location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return this.location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public int getBirthYear() {
        return this.birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getBirthMonth() {
        return this.birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public String getRequestIp() {
        return this.requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }


    public int getAppChannel() {
        return this.appChannel;
    }

    public void setAppChannel(int appChannel) {
        this.appChannel = appChannel;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public String getResourceCat() {
        return this.resourceCat;
    }

    public void setResourceCat(String resourceCat) {
        this.resourceCat = resourceCat;
    }

    public int getInterCode() {
        return this.interCode;
    }

    public void setInterCode(int interCode) {
        this.interCode = interCode;
    }

    public Integer getIsStaff() {
        return this.isStaff;
    }

    public void setIsStaff(Integer isStaff) {
        this.isStaff = isStaff;
    }
}
