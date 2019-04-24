package com.kkai.oauth.mapper;


import com.kkai.oauth.enity.RegisterPara;
import com.kkai.oauth.enity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Kkai on 2017/12/10.
 */
@Mapper
public interface UserMapper {

    UserInfo findUserInfoByName(String userName);


    int registerNormal(@Param("reg") RegisterPara registerPara);

    int findLastUserId();

}
