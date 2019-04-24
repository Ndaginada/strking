package com.example.demo.mapper;

import com.example.demo.entity.AddUserPara;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Kkai on 2017/12/10.
 */
@Mapper
public interface UserMapper {

    User getUserById(Integer id);

    User getUserByNameAndPassWord(@Param("username") String username, @Param("password") String password);

    int updateUserInfo(@Param("sid") Integer sid, @Param("username") String username, @Param("email") String email,
                       @Param("icon") String icon, @Param("introduce") String introduce);

    User findUserByUserName(String username);

    int updatePass(@Param("id") Integer sid, @Param("pass") String password);

    List<User> getUserList(@Param("username") String username, @Param("limit") int limit, @Param("offset") long offset);

    long countUserList(@Param("username") String username, @Param("limit") int limit, @Param("offset") long offset);

    int delUserById(@Param("userId") Integer userId);

    int delUserRoleById(@Param("userId")Integer userId);

    int addUser(@Param("para") AddUserPara para);

    User getUserByUserName(@Param("username") String username);

    int addUserRole(@Param("roleId") Integer userRole, @Param("userId") Integer sid);

    long countAdminList(@Param("username")String username);

    List<User> getAdminList(@Param("username")String username, @Param("limit") int limit, @Param("offset") long offset);
}
