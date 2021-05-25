package com.bookcity.test;

import com.bookcity.dao.ClientDao;
import com.bookcity.dao.impl.ClientDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientDaoTest {
    private ClientDao clientDao=new ClientDaoImpl();

    @Test
    public void findTotalRecordsByPrice() {
        System.out.println(clientDao.findTotalRecordsByPrice(10,50));
    }

    @Test
    public void findPageListByPrice() {
        System.out.println(clientDao.findPageListByPrice(10, 50, 0, 4));
    }
}