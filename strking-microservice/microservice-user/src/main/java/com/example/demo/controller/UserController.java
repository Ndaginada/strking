package com.example.demo.controller;

import com.example.demo.common.BaseListResp;
import com.example.demo.common.RespMsg;
import com.example.demo.common.RespStatus;
import com.example.demo.entity.*;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User
 *
 * @author Kkai
 * @date 2017/12/10
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "userInfo")
    public RespMsg getUserById(@RequestBody UserInfoPara userInfoPara) {
        RespMsg respMsg = new RespMsg();
        respMsg.setData(userService.getUserById(userInfoPara.getUserid()));
        return respMsg;
    }

    @RequestMapping("changeInfo")
    public RespMsg changeInfo(@RequestBody ChangeInfoPara changeInfoPara) {
        RespMsg respMsg = new RespMsg();
        if (!userService.checkChangeUserName(changeInfoPara.getUsername(), changeInfoPara.getSid())) {
            respMsg.setCode(RespStatus.BAD_REQUEST.code());
            respMsg.setMsg("用户名已存在");
            return respMsg;
        }

        if (!userService.changeInfo(changeInfoPara.getSid(), changeInfoPara.getUsername(),
                changeInfoPara.getEmail(), changeInfoPara.getIcon(), changeInfoPara.getIntroduce())) {
            respMsg.setCode(RespStatus.COMMON_ERROR.code());
            respMsg.setMsg("修改失败");
            return respMsg;
        }
        return respMsg;
    }

    @RequestMapping("changePass")
    public RespMsg changePass(@RequestBody ChangePassPara changePassPara) {
        RespMsg respMsg = new RespMsg();
        if (!userService.checkPass(changePassPara.getSid(), changePassPara.getOldPassword())) {
            respMsg.setCode(RespStatus.BAD_REQUEST.code());
            respMsg.setMsg("密码错误");
            return respMsg;
        }

        if (!userService.changePass(changePassPara.getSid(), changePassPara.getNewPassword())) {
            respMsg.setCode(RespStatus.COMMON_ERROR.code());
            respMsg.setMsg("修改失败");
            return respMsg;
        }
        return respMsg;
    }

    @RequestMapping("userList")
    public RespMsg userList(@RequestBody UserListPara req) {
        RespMsg respMsg = new RespMsg();
        BaseListResp<User> resp = new BaseListResp<>();
        resp.setItems(userService.getUserList(req.getUsername(), req.getLimit(), req.getOffset()));
        resp.setTotalCount(userService.countUserList(req.getUsername(), req.getLimit(), req.getOffset()));
        respMsg.data = resp;
        return respMsg;
    }

    @RequestMapping("adminList")
    public RespMsg adminList(@RequestBody UserListPara req) {
        RespMsg respMsg = new RespMsg();
        BaseListResp<User> resp = new BaseListResp<>();
        resp.setItems(userService.getAdminList(req.getUsername(), req.getLimit(), req.getOffset()));
        resp.setTotalCount(userService.countAdminList(req.getUsername()));
        respMsg.data = resp;
        return respMsg;
    }

    @RequestMapping("delUser")
    public RespMsg delUser(@RequestBody DelUserPara para) {
        RespMsg respMsg = new RespMsg();
        if (!userService.delUser(para.getUserId())) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        }
        return respMsg;
    }

    @RequestMapping("addUser")
    public RespMsg addUser(@RequestBody AddUserPara para) {
        RespMsg respMsg = new RespMsg();

        if (userService.getUserByUserName(para.getUsername()) != null) {
            respMsg.setCode(RespStatus.BAD_REQUEST.code());
            return respMsg;
        }

        if (!userService.addUser(para)) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
            return respMsg;
        }

        return respMsg;
    }
}
