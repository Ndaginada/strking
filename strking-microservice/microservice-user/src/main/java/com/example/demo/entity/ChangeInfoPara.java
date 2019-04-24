package com.example.demo.entity;

import javax.validation.constraints.NotNull;

/**
 * Created by Kkai on 2018/3/21.
 */
public class ChangeInfoPara {
    @NotNull
    private Integer sid;
    private String username;
    private String icon;
    private String email;
    private String introduce;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
