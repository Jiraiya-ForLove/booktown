package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        User user = userDao.queryUserByUsername("admin");
        if (user == null){
            System.out.println("该用户名可用");
        }else {
            System.out.println("该用户名已经被使用");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User user = userDao.queryUserByUsernameAndPassword("admin1", "admin");
        if (user == null){
            System.out.println("用户名或者密码错误");
        }else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
        int res = userDao.saveUser(new User(null, "admin01", "admin", "amin@163.com"));
        if (res == -1){
            System.out.println("保存失败");
        }else {
            System.out.println("添加成功");
        }
    }
}