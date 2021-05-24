package com.bookcity.dao;

import com.bookcity.entity.Book;

import java.util.List;

public interface BookDao {

    public int saveBook(Book book);

    public int deleteBook(Integer id);

    public int updateBook(Book book);

    public Book findById(Integer id);

    public List<Book> findAll();

    /**
     * 查询总的记录数
     * @return
     */
    public Integer findTotalRecords();

    /**
     * 分页查询
     * @param beginIndex 起始索引
     * @param pageSize  每页显示的记录数
     * @return
     */
    public List<Book> findPageList(int beginIndex,int pageSize );
}
