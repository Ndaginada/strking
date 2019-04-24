package com.example.demo.service;

import com.example.demo.entity.AddUserPara;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kkai on 2017/12/10.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User getUserById(Integer id) {
        User user = userMapper.getUserById(id);
        if (user.getIntroduce() == null || user.getIntroduce().isEmpty()) {
            user.setIntroduce("这个人很懒，没有简介");
        }
        return user;
    }

    public boolean changeInfo(Integer sid, String username, String email, String icon, String introduce) {

        int num = userMapper.updateUserInfo(sid, username, email, icon, introduce);
        return num > 0;
    }

    public boolean checkChangeUserName(String username, Integer sid) {
        User change = userMapper.findUserByUserName(username);
        User now = userMapper.getUserById(sid);
        if (change == null) {
            return true;
        } else {
            if (now.getUsername().equals(change.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean changePass(Integer sid, String newPassword) {
        int num = userMapper.updatePass(sid, newPassword);
        return num > 0;
    }

    public boolean checkPass(Integer sid, String oldPassword) {
        User user = userMapper.getUserById(sid);
        return user.getPassword().equals(oldPassword);
    }

    public List<User> getUserList(String username, int limit, long offset) {
        return userMapper.getUserList(username, limit, offset);
    }

    public long countUserList(String username, int limit, long offset) {
        return userMapper.countUserList(username, limit, offset);
    }

    public boolean delUser(Integer userId) {
        return
                userMapper.delUserById(userId) > 0 &&
                        userMapper.delUserRoleById(userId) > 0;
    }

    public boolean addUser(AddUserPara para) {
        return userMapper.addUser(para) > 0 &&
                userMapper.addUserRole(para.getUserRole(), userMapper.getUserByUserName(para.getUsername()).getSid()) > 0;
    }

    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    public List<User> getAdminList(String username, int limit, long offset) {
        return userMapper.getAdminList(username, limit, offset);
    }

    public long countAdminList(String username) {
        return userMapper.countAdminList(username);
    }
}
