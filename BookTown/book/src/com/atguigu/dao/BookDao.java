package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

public interface BookDao {

    int addBook(Book book);
    int deleteBookById(Integer id);
    int updateBook(Book book);
    Book queryBookById(Integer id);
    List<Book> queryBooks();

    Integer queryForTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);
}
