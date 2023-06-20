package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @program: BookTown
 * @description:
 * @author: shenhaha
 * @create: 2023-05-05 13:41
 **/

public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     * 添加书籍
     *
     * @param book
     * @return
     */
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    /**
     * 根据id删除书籍
     *
     * @param id
     * @return
     */
    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    /**
     * 更新指定书籍的信息
     *
     * @param book 需要传入book的id进行更新
     * @return
     */
    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    /**
     * 通过id查询指定的书籍
     * @param id
     * @return Book
     */
    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    /**
     * 查询所有的书籍
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    /**
     * 查询出表中的数量
     * @return
     */
    @Override
    public Integer queryForTotalCount() {
        String sql = "select count(*) from t_book";
        Object o = querySingleValue(sql);
        Number pageTotalCount = (Number) o;
        return pageTotalCount.intValue();
    }

    /**
     * 查询某页面的数据
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book limit ?,?";

        return queryForList(Book.class,sql,begin,pageSize);
    }
}
