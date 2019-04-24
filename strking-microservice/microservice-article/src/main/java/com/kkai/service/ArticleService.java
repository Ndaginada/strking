package com.kkai.service;

import com.kkai.common.BaseListReq;
import com.kkai.entity.ArticleListPara;
import com.kkai.entity.ArticleVo;
import com.kkai.entity.CollectVo;
import com.kkai.entity.SimpleArticleListVo;
import com.kkai.mapper.ArticleMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kkai on 2018/3/25.
 */
@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public boolean addArticle(String title, String titleImg, Integer authorId, String content, Integer typeId) {

        int num =  articleMapper.insertArticle(title, titleImg, authorId, content, typeId);
        return num > 0;
    }

    public ArticleVo findArticleById(Integer id) {
        return articleMapper.findArticleById(id);
    }

    public List<ArticleVo> findArticleList(ArticleListPara req) {

        return articleMapper.findArticleList(req.getArticle_title(),req.getLimit(),req.getOffset(),req.getType_id());
    }

    public long countArticleList(ArticleListPara req) {
        return articleMapper.countArticleList(req.getType_id(),req.getArticle_title());
    }

    public boolean changeArticleStatus(Integer article_id,Integer status) {
        return articleMapper.changeArticleStatus(article_id,status) > 0;
    }

    public boolean delArticleById(Integer id) {
        return articleMapper.delArticleById(id) > 0;
    }

    public boolean isShoucang(Integer article_id, Integer user_id) {
        return articleMapper.findCollectById(article_id,user_id) != null;
    }

    public boolean addShoucang(Integer article_id, Integer user_id) {
       return articleMapper.insertCollect(article_id,user_id) > 0;
    }

    public boolean cancelShoucang(Integer article_id, Integer user_id) {
        return articleMapper.delCollect(article_id,user_id) > 0;
    }

    public List<SimpleArticleListVo> myCollectList(Integer user_id, int limit, long offset) {
        return articleMapper.myCollectList(user_id,limit,offset);
    }

    public List<SimpleArticleListVo> myArticleList(Integer user_id, int limit, long offset) {
        return articleMapper.myArticleList(user_id,limit,offset);
    }

    public boolean browseArticle(Integer id) {
        return articleMapper.addArticleBrowse(id) > 0;
    }
}
