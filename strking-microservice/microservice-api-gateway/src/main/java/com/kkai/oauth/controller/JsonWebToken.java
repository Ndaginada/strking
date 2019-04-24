package com.kkai.oauth.controller;

import com.kkai.oauth.enity.*;
import com.kkai.oauth.mapper.RoleMapper;
import com.kkai.oauth.mapper.UserMapper;
import com.kkai.oauth.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Kkai on 2018/1/24.
 */
@RestController
@RequestMapping("/oauth")
public class JsonWebToken {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Audience audienceEntity;
    @Autowired
    private RoleMapper roleMapper;

    @GetMapping("/test")
    public String test() {
        UserInfo user = userMapper.findUserInfoByName("kkai");
        return user.getPassword();
    }

    /**
     * 注册
     *
     * @param registerPara
     * @return
     */
    @RequestMapping(value = "/register_normal", method = RequestMethod.POST)
    public RespMsg register(@RequestBody RegisterPara registerPara) {
        RespMsg respMsg = new RespMsg();
        UserInfo tableUserInfo = userMapper.findUserInfoByName(registerPara.getUsername());
        if (tableUserInfo != null) {
            respMsg.setCode(RespStatus.BAD_REQUEST.code());
            respMsg.setMsg("用户名重复");
            return respMsg;
        }
        int insertUserNum = userMapper.registerNormal(registerPara);
        int lastUserId = userMapper.findLastUserId();
        int insertRoleUserNum = roleMapper.insertRoleUser(lastUserId,1);
        if (insertUserNum <= 0 || insertRoleUserNum <= 0) {
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
        } else {
            respMsg.setMsg("注册成功");
        }
        return respMsg;
    }

    /**
     * 验证用户名密码  获取token
     *
     * @param loginPara
     * @return
     */
    @RequestMapping(value = "/token")
    public Object getAccessToken(@RequestBody LoginPara loginPara) {
        RespMsg resultMsg;
        if (loginPara.getClientId() == null
                || (loginPara.getClientId().compareTo(audienceEntity.getClientId()) != 0)) {
            resultMsg = new RespMsg(RespStatus.FORBIDDEN.code(),
                    RespStatus.FORBIDDEN.message(), RespStatus.FORBIDDEN.message());
            return resultMsg;
        }


        //验证用户名密码
        UserInfo user = userMapper.findUserInfoByName(loginPara.getUserName());
        if (user == null) {
            resultMsg = new RespMsg(RespStatus.BAD_USER.code(),
                    RespStatus.BAD_USER.message(), RespStatus.BAD_USER.message());
            return resultMsg;
        }
        //验证密码
        else {
            if (user.getPassword().compareTo(loginPara.getPassWord()) != 0) {
                resultMsg = new RespMsg(RespStatus.BAD_USER.code(),
                        RespStatus.BAD_USER.message(), RespStatus.BAD_USER.message());
                return resultMsg;
            }
            //超管登录
            if (loginPara.getLoginRole() != null) {
                if (loginPara.getLoginRole() == 1){
                    System.out.println(user.getRole());
                    if ( user.getRole().compareTo("SUPER_ADMIN")!= 0){
                        resultMsg = new RespMsg(RespStatus.UNAUTHORIZED.code(),
                                RespStatus.UNAUTHORIZED.message(), RespStatus.UNAUTHORIZED.message());
                        return resultMsg;
                    }
                }
            }


//                String md5Password = MyUtils.getMD5(loginPara.getPassWord()+user.getSalt());
//
//                if (md5Password.compareTo(user.getPassWord()) != 0)
//                {
//                    resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(),
//                            ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
//                    return resultMsg;
//                }
        }

        //拼装accessToken
        String accessToken = JwtHelper.createJWT(loginPara.getUserName(), String.valueOf(user.getUsername()),
                user.getRole(), audienceEntity.getClientId(), audienceEntity.getName(),
                audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());

        //返回accessToken
        AccessToken accessTokenEntity = new AccessToken();
        accessTokenEntity.setAccess_token(accessToken);
        accessTokenEntity.setExpires_in(audienceEntity.getExpiresSecond());
        accessTokenEntity.setToken_type("bearer");
        accessTokenEntity.setUser_id(user.getId());
        resultMsg = new RespMsg(RespStatus.OK.code(),
                RespStatus.OK.message(), RespStatus.OK.message());
        resultMsg.setData(accessTokenEntity);
        return resultMsg;

    }
}
