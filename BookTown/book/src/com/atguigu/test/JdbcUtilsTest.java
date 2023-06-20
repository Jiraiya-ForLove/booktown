package com.atguigu.test;

import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

/**
 * @program: BookTown
 * @description: 测试工具类
 * @author: shenhaha
 * @create: 2023-03-30 10:39
 **/

public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        for (int i = 0; i < 100; i++) {
            System.out.println(JdbcUtils.getConnection());
        }
    }
}
