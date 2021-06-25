package com.bookcity.service;

import com.bookcity.entity.Book;
import com.bookcity.entity.Page;

import java.util.List;

public interface BookService {

    public int saveBook(Book book);

    public void uploadCover();

    public int deleteBook(Integer id);

    public int updateBook(Book book);

    public Book findById(Integer id);

    public List<Book> findAll();

    /**
     * 分页功能
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Book> page(int pageNo,int pageSize);
}
