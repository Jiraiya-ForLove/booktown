package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLOutput;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "shenpei", "106331", "pei@163.com"));
    }

    @Test
    public void login() {
        User user = userService.login(new User(null, "admin123", "amin", "2435"));
        System.out.println(user);
    }

    @Test
    public void existsUsername() {
        boolean admin = userService.existsUsername("admin");
        if (admin) {
            System.out.println("用户名已经存在！");
        } else {
            System.out.println("用户名可用");
        }
    }
}