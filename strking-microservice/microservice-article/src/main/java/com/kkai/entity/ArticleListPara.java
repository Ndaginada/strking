package com.kkai.entity;

import com.kkai.common.BaseListReq;

/**
 * Created by Kkai on 2018/3/25.
 */
public class ArticleListPara extends BaseListReq{
    private String article_title;
    private Integer type_id;

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }
}
