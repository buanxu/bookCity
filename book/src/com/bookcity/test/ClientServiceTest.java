package com.bookcity.test;

import com.bookcity.dao.ClientDao;
import com.bookcity.dao.impl.ClientDaoImpl;
import com.bookcity.entity.Book;
import com.bookcity.service.ClientService;
import com.bookcity.service.impl.ClientServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClientServiceTest {

    private ClientService clientService=new ClientServiceImpl();

    @Test
    public void pageByPrice() {
        System.out.println(clientService.pageByPrice(10, 50, 1, 4));
    }
}