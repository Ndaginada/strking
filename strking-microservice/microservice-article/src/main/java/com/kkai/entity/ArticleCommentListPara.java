package com.kkai.entity;

import com.kkai.common.BaseListReq;

/**
 * Created by Kkai on 2018/4/2.
 */
public class ArticleCommentListPara extends BaseListReq{
    private Integer article_id;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }
}
