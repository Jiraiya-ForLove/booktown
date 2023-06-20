package com.atguigu.utils;

import com.sun.deploy.net.HttpRequest;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/**
 * @program: BookTown
 * @description:
 * @author: shenhaha
 * @create: 2023-04-20 09:41
 **/

public class WebUtils {
    public static  <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符类型转化为Int类型
     * @param str
     * @param defaultVal
     * @return
     */
    public static int parseInt(String str,int defaultVal){
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultVal;
    }
}
