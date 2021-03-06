package com.bookcity.web;

import com.bookcity.entity.Book;
import com.bookcity.entity.Cart;
import com.bookcity.entity.CartItem;
import com.bookcity.service.BookService;
import com.bookcity.service.impl.BookServiceImpl;
import com.bookcity.utils.Beanutils;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartController extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    //删除列表页面的商品
    public void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //先获取商品id
        int id = Beanutils.parseInt(req.getParameter("id"), 0);
        //从session中获取购物车信息
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            //删除购物车中指定的商品
            cart.deleteItem(id);
            //重定向到商品原来所在页面
            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    //通过ajax把列表页面的商品添加到购物车
    public void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //先获取商品id
        int id = Beanutils.parseInt(req.getParameter("id"), 0);
        //从数据库查询该商品信息
        Book book = bookService.findById(id);
        //把Book转换成CartItem
        CartItem cartItem=new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //把购物车放在session中，确保多次往同一个购物车里添加商品
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        //往购物车里添加商品
        cart.addItem(cartItem);

        //把最后一次添加到购物车的商品的名称添加到session
        req.getSession().setAttribute("lastName", cartItem.getName());

        //把最后一次添加到购物车的商品名称和购物车总商品数转成json格式的数据传回前台
        Integer cartTotalCounts = cart.getTotalCounts();
        String lastName=cartItem.getName();
        req.getSession().removeAttribute("cartTotalCounts");
        req.getSession().removeAttribute("lastName");
        req.getSession().setAttribute("cartTotalCounts",cartTotalCounts);
        req.getSession().setAttribute("lastName",lastName);
        //把数据封装在map里
        Map<String,Object> map=new HashMap<>();
        map.put("cartTotalCounts",cartTotalCounts);
        map.put("lastName",lastName);

        Gson gson=new Gson();
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }


    //把列表页面的商品添加到购物车
    public void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //先获取商品id
        int id = Beanutils.parseInt(req.getParameter("id"), 0);
        //从数据库查询该商品信息
        Book book = bookService.findById(id);
        //把Book转换成CartItem
        CartItem cartItem=new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //把购物车放在session中，确保多次往同一个购物车里添加商品
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        //往购物车里添加商品
        cart.addItem(cartItem);

        //把最后一次添加到购物车的商品的名称添加到session
        req.getSession().setAttribute("lastName", cartItem.getName());

        //重定向到商品原来所在页面
        resp.sendRedirect(req.getHeader("referer"));
    }

    //修改商品数量
    public void updateCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //先获取商品id
        int id = Beanutils.parseInt(req.getParameter("id"), 0);
        //获取商品的数量
        int counts = Beanutils.parseInt(req.getParameter("counts"), 0);
        //从session中获取购物车信息
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            //修改商品数量
            cart.updateItem(id,counts);
            //重定向到商品原来所在页面
            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    //清空购物车
    public void clear(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //从session中获取购物车信息
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clearCart();
            //重定向到商品原来所在页面
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}
