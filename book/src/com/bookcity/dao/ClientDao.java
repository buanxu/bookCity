package com.bookcity.dao;

import com.bookcity.entity.Book;

import java.util.List;

public interface ClientDao {
    /**
     * 按照价格区间查询总的记录数
     * @param min
     * @param max
     * @return
     */
    public Integer findTotalRecordsByPrice(int min,int max);

    /**
     * 按照价格区间查询所有的数据
     * @param beginIndex
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    public List<Book> findPageListByPrice(int min,int max ,int beginIndex, int pageSize);

}
