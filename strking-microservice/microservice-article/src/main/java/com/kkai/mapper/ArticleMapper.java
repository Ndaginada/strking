package com.kkai.mapper;

import com.kkai.entity.ArticleVo;
import com.kkai.entity.CollectVo;
import com.kkai.entity.SimpleArticleListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Kkai on 2018/3/25.
 */
@Mapper
public interface ArticleMapper {

    int insertArticle(@Param("title") String title, @Param("titleImg") String titleImg,
                      @Param("authorId") Integer authorId, @Param("content") String content,
                      @Param("typeId") Integer typeId);

    ArticleVo findArticleById(@Param("id") Integer id);

    List<ArticleVo> findArticleList(@Param("title") String article_title, @Param("limt") int limit, @Param("offset") long offset, @Param("type_id") Integer type_id);

    long countArticleList(@Param("type_id") Integer type_id, @Param("title") String article_title);

    int changeArticleStatus(@Param("id") Integer article_id, @Param("status") Integer status);

    int delArticleById(@Param("id") Integer id);

    CollectVo findCollectById(@Param("article_id") Integer article_id,@Param("user_id")  Integer user_id);

    int insertCollect(@Param("article_id") Integer article_id,@Param("user_id")  Integer user_id);

    int delCollect(@Param("article_id") Integer article_id,@Param("user_id")  Integer user_id);

    List<SimpleArticleListVo> myCollectList(@Param("user_id") Integer user_id, @Param("limit") int limit, @Param("offset") long offset);

    List<SimpleArticleListVo> myArticleList(@Param("user_id") Integer user_id, @Param("limit") int limit, @Param("offset") long offset);

    int addArticleBrowse(Integer id);
}
