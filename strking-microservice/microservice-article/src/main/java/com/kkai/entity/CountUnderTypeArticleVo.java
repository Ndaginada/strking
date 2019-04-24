package com.kkai.entity;

/**
 * Created by Kkai on 2018/4/10.
 */
public class CountUnderTypeArticleVo {
    private Integer type_id;
    private String type_content;
    private Integer under_num;

    public String getType_content() {
        return type_content;
    }

    public void setType_content(String type_content) {
        this.type_content = type_content;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getUnder_num() {
        return under_num;
    }

    public void setUnder_num(Integer under_num) {
        this.under_num = under_num;
    }
}
