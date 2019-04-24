package com.kkai.controller;

import com.kkai.common.RespMsg;
import com.kkai.common.RespStatus;
import com.kkai.entity.*;
import com.kkai.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kkai on 2018/3/25.
 */
@RestController
@RequestMapping("type")
public class TypeController {
    @Autowired
    TypeService typeService;
    @RequestMapping("list")
    public RespMsg list(){
        RespMsg respMsg = new RespMsg();
        List<TypeVo> type = typeService.findTypeList();
        respMsg.setData(type);
        return respMsg;
    }

    @RequestMapping("delType/{id}")
    public RespMsg delType(@PathVariable("id")  Integer id){
        RespMsg respMsg = new RespMsg();
        if (!typeService.delType(id)){
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }
    @RequestMapping("addType")
    public RespMsg addType(@RequestBody AddTypePara para){
        RespMsg respMsg = new RespMsg();
        if (!typeService.addType(para.getType_content())){
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }
    @RequestMapping("countUnderArticle")
    public RespMsg countUnderArticle(){
        RespMsg respMsg = new RespMsg();
        List<CountUnderTypeArticleVo> type = typeService.countUnderArticle();
        respMsg.setData(type);
        return respMsg;
    }

    @RequestMapping("getContent")
    public RespMsg getContent(@RequestParam TypeContentPara typeContentPara){
        RespMsg respMsg = new RespMsg();
        TypeVo type = typeService.getTypeContent(typeContentPara.getType_id());
        respMsg.setData(type);
        return respMsg;
    }
}
