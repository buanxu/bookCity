package com.bookcity.service;

import com.bookcity.dao.impl.ClientDaoImpl;
import com.bookcity.entity.Book;
import com.bookcity.entity.Page;

public interface ClientService {
    /**
     * 按照价格区间查询数据并返回分页模型
     * @param min
     * @param max
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Book> pageByPrice(int min,int max ,int pageNo, int pageSize);
}
