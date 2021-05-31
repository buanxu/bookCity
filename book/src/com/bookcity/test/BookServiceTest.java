package com.bookcity.test;

import com.bookcity.dao.BookDao;
import com.bookcity.dao.impl.BookDaoImpl;
import com.bookcity.entity.Book;
import com.bookcity.entity.Page;
import com.bookcity.service.BookService;
import com.bookcity.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService=new BookServiceImpl();

    /**
     * 由于filter加了事务，所以只有通过浏览器添加数据的时候才能成功保存在数据库里，在测试里没有触发事务，所以添加数据不会成功
     */
    @Test
    public void saveBook() {
        Book book=new Book("白夜行","东野圭吾",new BigDecimal(299.9),10000,9999,"D:/book/");
        bookService.saveBook(book);
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(31);
    }

    /**
     * 由于filter加了事务，所以只有通过浏览器添加数据的时候才能成功保存在数据库里，在测试里没有触发事务，所以添加数据不会成功
     */
    @Test
    public void updateBook() {
        Book book=new Book(32,"嫌疑人X的献身","东野圭吾",new BigDecimal(299.9),100000,99999,"D:/book/");
        bookService.updateBook(book);
    }

    @Test
    public void findById() {
        System.out.println(bookService.findById(32));
    }

    @Test
    public void findAll() {
        System.out.println(bookService.findAll().size());
    }

    @Test
    public void page(){
        Page<Book> page = bookService.page(1, 5);
        System.out.println(page);
    }

}