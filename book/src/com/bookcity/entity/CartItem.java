package com.bookcity.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车商品项模型
 */
public class CartItem implements Serializable {
    //商品id
    private Integer id;
    //商品名
    private String name;
    //当前商品数量
    private Integer counts;
    //商品单价
    private BigDecimal price;
    //当前商品总金额
    private BigDecimal totalPrice;

    public CartItem(Integer id, String name, Integer counts, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.counts = counts;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", counts=" + counts +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
