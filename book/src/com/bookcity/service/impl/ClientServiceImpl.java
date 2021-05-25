package com.bookcity.service.impl;

import com.bookcity.dao.ClientDao;
import com.bookcity.dao.impl.ClientDaoImpl;
import com.bookcity.entity.Book;
import com.bookcity.entity.Page;
import com.bookcity.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao=new ClientDaoImpl();

    @Override
    public Page<Book> pageByPrice(int min, int max, int pageNo, int pageSize) {
        Page<Book> page=new Page<Book>();

        //设置当前页码
        page.setPageNo(pageNo);
        //设置每页显示的数量
        page.setPageSize(pageSize);

        //设置

        //设置总的记录数
        int recordsCounts=clientDao.findTotalRecordsByPrice(min, max);
        page.setPageTotalCounts(recordsCounts);

        //设置总的页码数
        int pageCounts=recordsCounts/pageSize;
        if (recordsCounts%pageSize >0){
            pageCounts++;
        }
        page.setPageTotal(pageCounts);

        //设置每页显示的数据
        List<Book> books = clientDao.findPageListByPrice(min,max,(pageNo - 1)*pageSize,pageSize);
        page.setItems(books);

        return page;
    }
}
