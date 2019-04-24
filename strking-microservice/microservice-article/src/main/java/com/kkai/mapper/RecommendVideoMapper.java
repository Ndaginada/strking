package com.kkai.mapper;

import com.kkai.entity.ArticleVo;
import com.kkai.entity.RecommendVideoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Kkai on 2018/4/10.
 */
@Mapper
public interface RecommendVideoMapper {
    int addRecommendVideo(@Param("videoUrl") String videoUrl, @Param("title")String title, @Param("from_author")String from_author,@Param("add_admin_name")String add_admin_name);

    List<RecommendVideoVo> findRecommendVideoList(@Param("title") String title, @Param("limit") int limit, @Param("offset")long offset);

    long countRecommendVideoList(@Param("title") String title);

    int delArticleById(@Param("id") Integer id);
}
