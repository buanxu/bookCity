package com.bookcity.service.impl;

import com.bookcity.dao.BookDao;
import com.bookcity.dao.impl.BookDaoImpl;
import com.bookcity.entity.Book;
import com.bookcity.entity.Page;
import com.bookcity.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao=new BookDaoImpl();

    @Override
    public int saveBook(Book book) {
        return bookDao.saveBook(book);
    }

    @Override
    public void uploadCover() {

    }

    @Override
    public int deleteBook(Integer id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book findById(Integer id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    /**
     * 处理分页相关的的业务，返回分页模型
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page=new Page<Book>();

        //设置当前页码
        page.setPageNo(pageNo);
        //设置每页显示的数量
        page.setPageSize(pageSize);

        //设置总的记录数
        int recordsCounts=bookDao.findTotalRecords();
        page.setPageTotalCounts(recordsCounts);

        //设置总的页码数
        int pageCounts=recordsCounts/pageSize;
        if (recordsCounts%pageSize >0){
            pageCounts++;
        }
        page.setPageTotal(pageCounts);

        //设置每页显示的数据
        List<Book> bookList = bookDao.findPageList((pageNo - 1) * pageSize, pageSize);
        page.setItems(bookList);

        return page;
    }
}
