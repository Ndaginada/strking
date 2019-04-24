package com.kkai.entity;

/**
 * Created by Kkai on 2018/4/11.
 */
public class SimpleArticleListVo {
    private Integer id;
    private String title;
    private String title_img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle_img() {
        return title_img;
    }

    public void setTitle_img(String title_img) {
        this.title_img = title_img;
    }
}
