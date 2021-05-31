package com.bookcity.web;

import com.bookcity.entity.Book;
import com.bookcity.entity.Page;
import com.bookcity.service.BookService;
import com.bookcity.service.ClientService;
import com.bookcity.service.impl.BookServiceImpl;
import com.bookcity.service.impl.ClientServiceImpl;
import com.bookcity.utils.BeanUtils;
import com.sun.security.ntlm.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientController extends BaseServlet {
    //按照价格区间分页的service
    private ClientService clientService=new ClientServiceImpl();
    //book正常分页的service
    private BookService bookService=new BookServiceImpl();

    /**
     * 分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取前台传过来的参数，把前台传过来的数字字符串转成int类型
        int pageNo= BeanUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=BeanUtils.parseInt(req.getParameter("pageSize"), Page.BOOK_PAGE_SIZE);
        //调用service获取Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        //设置分页条请求的url
        page.setUrl("client/clientController?action=page");
        //把page存到request域
        req.setAttribute("page", page);
        //转发到list页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * 分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取前台传过来的参数，把前台传过来的数字字符串转成int类型
        int pageNo= BeanUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=BeanUtils.parseInt(req.getParameter("pageSize"), Page.BOOK_PAGE_SIZE);
        int min=BeanUtils.parseInt(req.getParameter("min"), 0);
        int max=BeanUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        System.out.println("min："+min);
        System.out.println("max："+max);
        //调用service获取Page对象
        Page<Book> page = clientService.pageByPrice(min,max,pageNo, pageSize);

        StringBuffer sb=new StringBuffer("client/clientController?action=pageByPrice");
        //如果有最小值参数，则追加到分页条的地址参数中
        if (req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        //如果有最小值参数，则追加到分页条的地址参数中
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }

        //设置分页条请求的url
        page.setUrl(sb.toString());
        //把page存到request域
        req.setAttribute("page", page);
        //转发到list页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}
