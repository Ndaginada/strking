package com.kkai.oauth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Kkai on 2018/3/20.
 */
@Mapper
public interface RoleMapper {
    int insertRoleUser(@Param("userId") int lastUserId, @Param("roleId") int roleId);
}
