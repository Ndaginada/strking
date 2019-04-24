package com.kkai.mapper;

import com.kkai.entity.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Kkai on 2018/4/2.
 */
@Mapper
public interface CommentMapper {

    void insertComment(@Param("article_id") Integer article_id, @Param("content") String content, @Param("user_id") Integer user_id);

    List<CommentVo> selectArticleCommentList(@Param("article_id") Integer article_id, @Param("limit") int limit, @Param("offset") long offset);

    int deleteCommentById(@Param("id") Integer id);
}
