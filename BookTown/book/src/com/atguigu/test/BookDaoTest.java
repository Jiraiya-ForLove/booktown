package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
//        bookDao.addBook(new Book(null,"线性代数","shenpei",new BigDecimal("80.01"),100,0,null));
        bookDao.addBook(new Book(null,"线性代数","沈沛",new BigDecimal("80.01"),100,0,null));

    }

    @Test
    public void deleteBookById() {
         bookDao.deleteBookById(4);
    }

    @Test
    public void updateBook() {
        int i = bookDao.updateBook(new Book(4, "考研政治", "shenpei", new BigDecimal("70.00"), 90, 10, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(5);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForTotalCount() {
        System.out.println(bookDao.queryForTotalCount());
    }


    @Test
    public void queryForPageItems() {
        for (Book queryForPageItem : bookDao.queryForPageItems(4, Page.PAGE_SIZE)) {
            System.out.println(queryForPageItem);
        }
    }
}