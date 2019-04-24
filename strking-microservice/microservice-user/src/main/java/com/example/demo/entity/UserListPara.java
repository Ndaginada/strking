package com.example.demo.entity;

import com.example.demo.common.BaseListReq;

/**
 * Created by Kkai on 2018/4/8.
 */
public class UserListPara extends BaseListReq {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
