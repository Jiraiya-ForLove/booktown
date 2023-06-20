package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserDao {

    /**
     * 注册：验证用户名是否有效
     * 根据用户名查询用户信息
     * @param username
     * @return 如果返回null，说明没有这个用户，反之亦然
     */
    public User queryUserByUsername(String username);

    /**
     * 登录：用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null，说明用户名或者密码错误
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 注册：保存用户信息
     * @param user
     * @return 返回-1，说明保存失败
     */
    public int saveUser(User user);
}
