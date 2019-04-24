package com.kkai.service;

import com.kkai.entity.CommentVo;
import com.kkai.mapper.CommentMapper;
import com.kkai.utility.SensitivewordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kkai on 2018/4/2.
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    public void addComment(Integer article_id, String content, Integer user_id) {
        SensitivewordFilter filter = SensitivewordFilter.getInstance();
        //替换后
        String hou = filter.replaceSensitiveWord(content, 1, "*");
        commentMapper.insertComment(article_id,hou,user_id);
    }

    public List<CommentVo> getArticleCommentList(Integer article_id, int limit, long offset) {
        return commentMapper.selectArticleCommentList(article_id,limit,offset);
    }

    public boolean delCommentById(Integer id) {
        return commentMapper.deleteCommentById(id) > 0;
    }
}
