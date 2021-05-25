package com.bookcity.dao.impl;

import com.bookcity.dao.ClientDao;
import com.bookcity.entity.Book;

import java.util.List;

public class ClientDaoImpl extends BaseDao implements ClientDao {
    @Override
    public Integer findTotalRecordsByPrice(int min, int max) {
        String sql="select count(name) from t_book where price between ? and ?";
        Number counts = (Number) findSingleValue(sql, min, max);
        return counts.intValue();
    }

    @Override
    public List<Book> findPageListByPrice(int min, int max,int beginIndex, int pageSize) {
        String sql="SELECT * FROM t_book  WHERE price  BETWEEN ? AND ? ORDER BY price ASC LIMIT ?,?";
        List<Book> books = findList(Book.class,sql,min,max,beginIndex, pageSize);
        return books;
    }
}
