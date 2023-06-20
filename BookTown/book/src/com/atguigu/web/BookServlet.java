package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: BookTown
 * @description:
 * @author: shenhaha
 * @create: 2023-05-05 15:43
 **/

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 显示图书管理列表页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        request.setAttribute("books",books);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    /**
     * 图书添加页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将获取的booK信息封装到book对象中
        Book book = WebUtils.copyParamToBean(request.getParameterMap(),new Book());
        //保存到数据库中
        bookService.addBook(book);
        //保存成功后返回到列表页面
        response.sendRedirect(request.getContextPath()+ "/manager/bookServlet?action=list");
    }

    /**
     * 删除图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取传送过来的书本的id
        String id = request.getParameter("id");
        int idNum = WebUtils.parseInt(id, 0);
        //从数据库中删除书籍
        bookService.deleteBookById(idNum);
        //重定向到列表页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
    }

    /**
     * 获取指定id的图书信息,并且回显到编辑页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }


    /**
     * 实现编辑图书的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=list");
    }

    /**
     * 实现分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int PageSize = WebUtils.parseInt(request.getParameter("PageSize"), Page.PAGE_SIZE);

        Page<Book> page = bookService.page(pageNo,PageSize);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
}
