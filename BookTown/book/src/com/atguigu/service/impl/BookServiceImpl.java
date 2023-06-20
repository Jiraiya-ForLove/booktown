package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

/**
 * @program: BookTown
 * @description:
 * @author: shenhaha
 * @create: 2023-05-05 15:37
 **/

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();
    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();
        //设置当前页码
        bookPage.setPageNo(pageNo);
        //设置当前页显示数量
        bookPage.setPageSize(pageSize);
        //设置总记录数
        Integer pagetotalCount = bookDao.queryForTotalCount();
        bookPage.setPageTotalCount(pagetotalCount);
        //设置总页码
        Integer pageTotal = pagetotalCount/pageSize;
        if (pagetotalCount%pageSize!=0){
            pageTotal++;
        }
        bookPage.setPageTotal(pageTotal);
        //设置当前页数据
        int begin = (pageNo-1)*pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        bookPage.setItems(items);
        return bookPage;
    }

}
