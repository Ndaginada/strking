package com.kkai.entity;

import com.kkai.common.BaseListReq;

/**
 * Created by Kkai on 2018/4/10.
 */
public class RecommendVideoListPara extends BaseListReq {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
