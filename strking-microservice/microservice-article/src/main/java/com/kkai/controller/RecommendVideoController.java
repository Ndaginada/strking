package com.kkai.controller;

import com.kkai.common.BaseListResp;
import com.kkai.common.RespMsg;
import com.kkai.common.RespStatus;
import com.kkai.entity.*;
import com.kkai.service.RecommendVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kkai on 2018/4/10.
 */
@RestController
@RequestMapping("recommendVideoController")
public class RecommendVideoController {

    @Autowired
    RecommendVideoService recommendVideoService;

    @RequestMapping("addRecommendVideo")
    public RespMsg addRecommendVideo(@RequestBody AddRecommendVideoPara para) {
        RespMsg respMsg = new RespMsg();
        if (!recommendVideoService.addRecommendVideo(para)) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }

//    @RequestMapping("recommendList")
//    public RespMsg addRecommendVideo(@RequestBody AddRecommendVideoPara para){
//        RespMsg respMsg = new RespMsg();
//        if (!recommendVideoService.addRecommendVideo(para)){
//            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
//        }
//        return respMsg;
//    }

    @RequestMapping("recommendList")
    public RespMsg list(@RequestBody RecommendVideoListPara req) {
        RespMsg respMsg = new RespMsg();
        BaseListResp<RecommendVideoVo> listResp = new BaseListResp<>();
        List<RecommendVideoVo> list = recommendVideoService.findRecommendVideoList(req);
        listResp.setTotalCount(recommendVideoService.countRecommendVideoList(req));
        listResp.setItems(list);
        respMsg.setData(listResp);
        return respMsg;
    }

    @RequestMapping("delRecommend/{id}")
    public RespMsg changeArticleStatus(@PathVariable("id") Integer id) {
        RespMsg respMsg = new RespMsg();
        if (!recommendVideoService.delRecommendById(id)) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }


}
