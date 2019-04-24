package com.kkai.entity;

/**
 * Created by Kkai on 2018/4/10.
 */
public class ChangeArticleStatusPara {
    private Integer article_id;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }
}
