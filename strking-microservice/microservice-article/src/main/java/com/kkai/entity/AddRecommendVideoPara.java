package com.kkai.entity;

/**
 * Created by Kkai on 2018/4/10.
 */
public class AddRecommendVideoPara {
    private String title;
    private String video_url;
    private String from_author;
    private String add_admin_name;

    public String getAdd_admin_name() {
        return add_admin_name;
    }

    public void setAdd_admin_name(String add_admin_name) {
        this.add_admin_name = add_admin_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getFrom_author() {
        return from_author;
    }

    public void setFrom_author(String from_author) {
        this.from_author = from_author;
    }
}
