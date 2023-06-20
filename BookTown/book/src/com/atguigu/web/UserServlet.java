package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServlet extends BaseServlet {

    public UserServlet() {
    }

    private UserService userService =  new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * 用于登陆操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //将获取的参数封装到bean里面
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        User userTest = userService.login(user);
        if (userTest == null){
            System.out.println("用户名或者密码错误");
            //将错误的信息、用户名进行回显
            req.setAttribute("errorMsg","用户名或者密码错误");
            req.setAttribute("username",username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }


    /**
     * 用户注册操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String authcode = req.getParameter("authcode");
        //将获取的参数封装到bean中

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        System.out.println(user);

        //判断验证码是否正确
        //这里的验证码是写死的，比如说为“abcde”
        if ("abcde".equals(authcode)){
            //验证码正确，继续检查当前用户名是否可用
            //web层面，不呢调用Dao层，只能调用Service
            if (userService.existsUsername(username)){
                //用户名已经存在，打印到控制台
                System.out.println("用户名已经存在");

                //对错误信息、用户名、邮箱进行回显
                req.setAttribute("errorMsg","用户名已经存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
            else {
                //用户名不存在，可以注册
                System.out.println("注册成功");
                userService.registUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            //验证码错误，跳回到注册页面
            req.setAttribute("errorMsg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
