package com.kkai.oauth.enity;

/**
 * Created by Kkai on 2018/1/24.
 */
public class LoginPara {
    private String clientId;
    private String userName;
    private String passWord;
    private String captchaCode;
    private String captchaValue;
    private Integer loginRole;

    public void setLoginRole(Integer loginRole) {
        this.loginRole = loginRole;
    }

    public Integer getLoginRole() {
        return loginRole;
    }


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }
}
