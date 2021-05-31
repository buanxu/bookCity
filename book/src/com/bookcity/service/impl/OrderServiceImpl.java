package com.bookcity.service.impl;

import com.bookcity.dao.BookDao;
import com.bookcity.dao.OrderDao;
import com.bookcity.dao.OrderItemDao;
import com.bookcity.dao.impl.BookDaoImpl;
import com.bookcity.dao.impl.OrderDaoImpl;
import com.bookcity.dao.impl.OrderItemDaoImpl;
import com.bookcity.entity.*;
import com.bookcity.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    public String createOrder(Cart cart, Integer userId) {
        //保证订单的唯一性
        String orderId = UUID.randomUUID().toString().replace("-", "");
        //创建订单
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), Order.NOT_SEND_OUT, userId);
        //保存订单
        orderDao.save(order);

//        int i=10/0;

        //遍历购物车，把其中的每一件商品都转换成订单项
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(cartItem.getId(), cartItem.getName(),
                    cartItem.getCounts(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            //保存订单项到数据库
            orderItemDao.save(orderItem);

            Book book = bookDao.findById(cartItem.getId());
            //每次创建订单，就更新相应商品的销量和库存
            book.setSales(book.getSales() + cartItem.getCounts());
            book.setStock(book.getStock() - cartItem.getCounts());
            bookDao.updateBook(book);
        }

        //结账创建完订单后要清空购物车
        cart.clearCart();

        return orderId;
    }

    @Override
    public List<Order> findOrder(Integer userId) {
        return orderDao.findOrder(userId);
    }

    @Override
    public List<OrderItem> findOrderItem(String orderId) {
        return orderItemDao.findOrderItem(orderId);
    }

    @Override
    public UserOrder findOneUserOrder(String orderId) {
        return orderDao.findOneUserOrder(orderId);
    }

    @Override
    public List<UserOrder> findUserOrders(String username) {
        return orderDao.findUserOrder(username);
    }

    /**
     * 修改订单状态
     * @param orderId
     * @param operate
     */
    @Override
    public void updateOrderStatus(String orderId, String operate) {

        //判断订单操作
        if ("sendOut".equals(operate)){
            //已发货
            orderDao.updateOrderStatus(Order.SEND_OUT,orderId);
        }else if ("receive".equals(operate)){
            //已签收
            orderDao.updateOrderStatus(Order.RECEIVE,orderId);
        }
    }

    /**
     * 处理分页相关的的业务，返回分页模型
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<UserOrder> page(int pageNo, int pageSize) {
        Page<UserOrder> page=new Page<UserOrder>();

        //设置当前页码
        page.setPageNo(pageNo);
        //设置每页显示的数量
        page.setPageSize(pageSize);

        //设置总的记录数
        int recordsCounts=orderDao.findTotalRecords();
        page.setPageTotalCounts(recordsCounts);

        //设置总的页码数
        int pageCounts=recordsCounts/pageSize;
        if (recordsCounts%pageSize >0){
            pageCounts++;
        }
        page.setPageTotal(pageCounts);

        //设置每页显示的数据
        List<UserOrder> userOrders = orderDao.findPageList((pageNo - 1) * pageSize, pageSize);
        page.setItems(userOrders);

        return page;
    }
}
