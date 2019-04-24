package com.kkai.controller;

import com.kkai.common.BaseListResp;
import com.kkai.common.RespMsg;
import com.kkai.common.RespStatus;
import com.kkai.entity.*;
import com.kkai.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kkai on 2018/3/24.
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("list")
    public RespMsg list(@RequestBody ArticleListPara req) {
        RespMsg respMsg = new RespMsg();
        BaseListResp<ArticleVo> listResp = new BaseListResp<>();
        List<ArticleVo> list = articleService.findArticleList(req);
        listResp.setTotalCount(articleService.countArticleList(req));
        listResp.setItems(list);
        respMsg.setData(listResp);
        return respMsg;
    }

    @RequestMapping("details/{id}")
    public RespMsg details(@PathVariable("id") Integer id) {
        RespMsg respMsg = new RespMsg();
        ArticleVo article = articleService.findArticleById(id);
        respMsg.setData(article);
        return respMsg;

    }

    @RequestMapping("edit")
    public RespMsg edit(@RequestBody AddArticlePara addArticlePara) {
        RespMsg respMsg = new RespMsg();
        boolean is_insert = articleService.addArticle(addArticlePara.getTitle(),
                addArticlePara.getTitleImg(), addArticlePara.getAuthorId(),
                addArticlePara.getContent(), addArticlePara.getTypeId()
        );
        if (!is_insert) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
            respMsg.setMsg("添加失败");
            return respMsg;
        }
        return respMsg;
    }

    //    @RequestMapping("changeArticleStatus")
//    public RespMsg changeArticleStatus(){
//        RespMsg respMsg = new RespMsg();
//        return respMsg;
//    }

    @RequestMapping("changeArticleStatus")
    public RespMsg changeArticleStatus(@RequestBody ChangeArticleStatusPara para) {
        RespMsg respMsg = new RespMsg();
        if (!articleService.changeArticleStatus(para.getArticle_id(), para.getStatus())) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }

    @RequestMapping("delArticle/{id}")
    public RespMsg delArticle(@PathVariable("id") Integer id) {
        RespMsg respMsg = new RespMsg();
        if (!articleService.delArticleById(id)) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }

    @RequestMapping("browseArticle/{id}")
    public RespMsg browseArticle(@PathVariable("id") Integer id) {
        RespMsg respMsg = new RespMsg();
        if (!articleService.browseArticle(id)) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }

    @RequestMapping("isShoucang")
    public RespMsg isShoucang(@RequestBody CollectPara para) {
        RespMsg respMsg = new RespMsg();
        respMsg.setData(articleService.isShoucang(para.getArticle_id(), para.getUser_id()));
        return respMsg;
    }

    @RequestMapping("addShoucang")
    public RespMsg addShoucang(@RequestBody CollectPara para) {
        RespMsg respMsg = new RespMsg();
        if (!articleService.addShoucang(para.getArticle_id(), para.getUser_id())) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }

    @RequestMapping("cancelShoucang")
    public RespMsg cancelShoucang(@RequestBody CollectPara para) {
        RespMsg respMsg = new RespMsg();
        if (!articleService.cancelShoucang(para.getArticle_id(), para.getUser_id())) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }

    @RequestMapping("myCollectList")
    public RespMsg myCollectList(@RequestBody SimpleArticleListPara para) {
        RespMsg respMsg = new RespMsg();
        BaseListResp<SimpleArticleListVo> resp = new BaseListResp<>();
        List<SimpleArticleListVo> list = articleService.myCollectList(para.getUser_id(), para.getLimit(), para.getOffset());
        resp.setItems(list);
        respMsg.data = resp;
        return respMsg;
    }

    @RequestMapping("myArticleList")
    public RespMsg myArticleList(@RequestBody SimpleArticleListPara para) {
        RespMsg respMsg = new RespMsg();
        BaseListResp<SimpleArticleListVo> resp = new BaseListResp<>();
        List<SimpleArticleListVo> list = articleService.myArticleList(para.getUser_id(), para.getLimit(), para.getOffset());
        resp.setItems(list);
        respMsg.data = resp;
        return respMsg;
    }
}
