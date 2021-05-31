package com.bookcity.test;

import com.bookcity.dao.BookDao;
import com.bookcity.dao.impl.BookDaoImpl;
import com.bookcity.entity.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao=new BookDaoImpl();

    /**
     * 由于filter加了事务，所以只有通过浏览器添加数据的时候才能成功保存在数据库里，在测试里没有触发事务，所以添加数据不会成功
     */
    @Test
    public void saveBook() {
        Book book=new Book("陆小凤传奇","古龙",new BigDecimal(99.9),100000,99999,"D:/book/");
        bookDao.saveBook(book);
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBook(30);
    }

    /**
     * 由于filter加了事务，所以只有通过浏览器添加数据的时候才能成功保存在数据库里，在测试里没有触发事务，所以添加数据不会成功
     */
    @Test
    public void updateBook() {
        Book book=new Book(31,"陆小凤传奇之凤舞九天","古龙",new BigDecimal(199.99),100000,99999,"D:/book/");
        bookDao.updateBook(book);
    }

    @Test
    public void findById() {
        System.out.println(bookDao.findById(31));
    }

    @Test
    public void findAll() {
        System.out.println(bookDao.findAll().size());
    }

    @Test
    public void findTotalRecords() {
        Number number=(Number) bookDao.findTotalRecords();
        System.out.println( number.intValue());
    }

    @Test
    public void findPageList() {
        List<Book> books = bookDao.findPageList(0, 5);
        System.out.println(books);
        System.out.println(books.size());
    }
}