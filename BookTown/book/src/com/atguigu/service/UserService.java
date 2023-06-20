package com.atguigu.service;

import com.atguigu.pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    void registUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 检查用户名是否存在
     * @param username
     * @return 如果返回true，代表用户名存在；返回false，代表用户名可用
     */
    boolean existsUsername(String username);

}
