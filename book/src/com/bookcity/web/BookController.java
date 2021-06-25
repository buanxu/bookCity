package com.bookcity.web;

import com.bookcity.entity.Book;
import com.bookcity.entity.Page;
import com.bookcity.service.BookService;
import com.bookcity.service.impl.BookServiceImpl;
import com.bookcity.utils.Beanutils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookController extends BaseServlet {
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
        String page1=req.getParameter("pageNo");
        String page2=req.getParameter("pageSize");
        int pageNo= Beanutils.parseInt(req.getParameter("pageNo"),1);
        int pageSize= Beanutils.parseInt(req.getParameter("pageSize"), Page.BOOK_PAGE_SIZE);
        //调用service获取Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        //设置分页条请求的url
        page.setUrl("manager/bookController?action=page");
        //把page存到request域
        req.setAttribute("page", page);
        //转发到list页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询所有的图书信息，并添加到request域中
        List<Book> books = bookService.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //处理上传文件时中文乱码的问题
        req.setCharacterEncoding("UTF-8");

        String fileType=null;
        int index=0;
        Map<String,String[]> requestMap=new HashMap<>();
        if(ServletFileUpload.isMultipartContent(req)){
            FileItemFactory fileItemFactory=new DiskFileItemFactory();
            ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);
            try {
                //parseRequest()在解析表单时，只会解析含有name属性的表单项并返回到List中，没有name属性的表单项不会解析
                //上传文件时是以二进制的形式上传的，即enctype="multipart/form-data"，
                // 参数也是以二进制的形式传递的，直接用request.getParameter()获取不到，必须用以下形式才能获取到参数
                List<FileItem>  items=servletFileUpload.parseRequest(req);
                for (FileItem item: items){
                    //普通表单项
                    if(item.isFormField()){
                        //把参数封装成Map
                        String name=item.getFieldName();
                        String value=item.getString("UTF-8");
                        String [] values={value};
                        requestMap.put(name, values);
                    }else {
                        //获取上传的图片的格式
                        fileType = item.getContentType();
                        String[] strings = fileType.split("/");
                        fileType=strings[strings.length-1];

                        //把当前上传的文件保存在服务器的指定目录
                        String uploadPath=req.getSession().getServletContext().getRealPath("/static/img/");
                        File f=new File(uploadPath);
                        //获取文件夹中商品封面的数目
                        index = f.list().length - 1;
                        String filePath=uploadPath+"default"+index+"."+fileType;
                        item.write(new File(filePath));

                        //把图片路径封装到Map
                        String img_path="static/img/default"+index+"."+fileType;
                        String[] p={img_path};
                        requestMap.put("img_path", p);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //每次添加一条数据后，都跳到最后一页以显示新添加的数据
        //当添加数据时页面显示了5条数据时，pageNo就要加1，小于5时候pageNo不变，以保证添加完数据后能跳转到最后一页
        int curPageCounts= Beanutils.parseInt(req.getParameter("curPageCounts"),0);
        int pageNo= Beanutils.parseInt(req.getParameter("pageNo"),0);
        if (curPageCounts==5){
            pageNo+=1;
        }

        //绑定前台传过来的参数，将参数封装到bean
        Book book = Beanutils.copyParamsToBean(new Book(),requestMap);

        bookService.saveBook(book);
        //添加完以后再通过请求list重定向到list页面，不能用请求转发会有bug
        resp.sendRedirect(req.getContextPath()+"/manager/bookController?action=page&pageNo="+pageNo);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //删除某条数据时，要保证删除后仍显示当前页
        int curPageCounts= Beanutils.parseInt(req.getParameter("curPageCounts"),0);
        int pageTotal= Beanutils.parseInt(req.getParameter("pageTotal"),0);
        int pageNo= Beanutils.parseInt(req.getParameter("pageNo"),0);
        //当前页码是最后一页且只有一条数据，则删除后跳到上一页；否则跳转到当前页
        if (pageNo==pageTotal && curPageCounts==1){
            pageNo-=1;
        }

        //把前台传来的字符串类型的id转换成int类型
        int id = Beanutils.parseInt(req.getParameter("id"), 0);

        bookService.deleteBook(id);

        //重定向到list页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookController?action=page&pageNo="+pageNo);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //修改当前页的某条数据后，仍然跳转到该页
        int pageNo= Beanutils.parseInt(req.getParameter("pageNo"),0);

        //先绑定请求参数
        Book book= Beanutils.copyParamsToBean(new Book(), req.getParameterMap());
        bookService.updateBook(book);
        //修改完转发到list页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookController?action=page&pageNo="+pageNo);
    }


    public void getBookInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要修改的bean的id
        int id= Beanutils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.findById(id);
        //把bean的信息回显
        req.setAttribute("book",book);
        //转发到修改页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }
}
