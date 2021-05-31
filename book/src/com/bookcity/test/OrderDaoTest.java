package com.bookcity.test;

import com.bookcity.dao.OrderDao;
import com.bookcity.dao.impl.OrderDaoImpl;
import com.bookcity.entity.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    private OrderDao orderDao = new OrderDaoImpl();

    /**
     * 由于filter加了事务，所以只有通过浏览器添加数据的时候才能成功保存在数据库里，在测试里没有触发事务，所以添加数据不会成功
     */
    @Test
    public void save() {

        orderDao.save(new Order("267834920758347",new Date(),new BigDecimal(108),0, 8));
    }

    @Test
    public void findOrder() {
        System.out.println(orderDao.findOrder(8));
    }
}