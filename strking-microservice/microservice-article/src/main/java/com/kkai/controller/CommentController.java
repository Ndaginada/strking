package com.kkai.controller;

import com.kkai.common.BaseListResp;
import com.kkai.common.RespMsg;
import com.kkai.common.RespStatus;
import com.kkai.entity.AddCommentPara;
import com.kkai.entity.ArticleCommentListPara;
import com.kkai.entity.CommentVo;
import com.kkai.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kkai on 2018/3/28.
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @RequestMapping("addComment")
    public RespMsg addComment(@RequestBody AddCommentPara addCommentPara){
        RespMsg respMsg = new RespMsg();
        commentService.addComment(addCommentPara.getArticle_id(),addCommentPara.getContent(),addCommentPara.getUser_id());
        return respMsg;
    }

    @RequestMapping("articleCommentList")
    public RespMsg ArticleCommentList(@RequestBody ArticleCommentListPara articleCommentListPara){
        RespMsg respMsg = new RespMsg();
        BaseListResp<CommentVo> data = new BaseListResp<>();
        List<CommentVo> item = commentService.getArticleCommentList(articleCommentListPara.getArticle_id(), articleCommentListPara.getLimit(), articleCommentListPara.getOffset());
        data.setItems(item);
        respMsg.setData(data);
        return respMsg;
    }

    @RequestMapping("delComment/{id}")
    public RespMsg delArticle(@PathVariable("id") Integer id) {
        RespMsg respMsg = new RespMsg();
        if (!commentService.delCommentById(id)) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }
}
