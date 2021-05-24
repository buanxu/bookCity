package com.bookcity.dao.impl;

import com.bookcity.dao.BookDao;
import com.bookcity.entity.Book;
import org.apache.commons.dbutils.QueryRunner;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int saveBook(Book book) {
        String sql="INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) VALUES(?,?,?,?,?,?);";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int deleteBook(Integer id) {
        String sql="delete from t_book where id =? ";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="UPDATE t_book SET `name`= ?, `author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? WHERE `id`=?";
        return update(sql, book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book findById(Integer id)
    {
        String sql="SELECT id, NAME, author, price, sales, stock, img_path FROM t_book where id=?";
        return findOne(Book.class, sql, id);
    }

    @Override
    public List<Book> findAll()
    {
        String sql="select * from t_book";
        return findList(Book.class, sql);
    }

    @Override
    public Integer findTotalRecords() {
        String sql="select count(name) from t_book";
        Number number=(Number) findSingleValue(sql);
        return number.intValue();
    }

    @Override
    public List<Book> findPageList(int beginIndex, int pageSize) {
        String sql="select * from t_book limit ?,?";
        List<Book> books = findList(Book.class, sql, beginIndex, pageSize);
        return books;
    }
}
