package com.kkai.entity;

import com.kkai.common.BaseListReq;

/**
 * Created by Kkai on 2018/4/11.
 */
public class SimpleArticleListPara extends BaseListReq{
    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
