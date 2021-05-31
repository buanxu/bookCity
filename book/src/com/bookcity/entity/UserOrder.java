package com.bookcity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserOrder implements Serializable {

    //设置能表示订单状态的三个常量
    public static final Integer NOT_SEND_OUT =0;
    public static final Integer SEND_OUT=1;
    public static final Integer RECEIVE=2;

    //用户id
    private Integer id;
    private String username;

    //用户订单信息
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    //0表示未发货，1表示已发货，2表示已签收
    private Integer status;
    private Integer userId;

    public UserOrder() {
    }

    public UserOrder(Integer id, String username, String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.id = id;
        this.username = username;
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
