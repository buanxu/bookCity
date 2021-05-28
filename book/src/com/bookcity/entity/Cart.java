package com.bookcity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车模型
 */
public class Cart implements Serializable {
    //总的商品数量
    //private Integer totalCounts;
    //总金额
    //private BigDecimal totalPrice;
    //商品项列表，让商品id作key，商品作value，方便查找
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品
     * @param item
     */
    public void addItem(CartItem item){
        //添加之前先查看购物车里是否已添加了该商品，若添加了就只更改商品数量和金额，没添加就直接添加

        //从购物车里查找当前要添加的商品
        CartItem cartItem = items.get(item.getId());

        if (cartItem==null){//表示购物车里没添加该商品
            items.put(item.getId(), item);
        }else {//否则已添加
            //更新商品数量
            cartItem.setCounts(cartItem.getCounts()+1);
            //更新商品总金额
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCounts())));
        }
    }

    /**
     *删除购物车中指定商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 修改商品项
     * @param id
     * @param counts
     */
    public void updateItem(Integer id,Integer counts){
        //先查看购物车中是否有此商品，有的话就更改

        CartItem cartItem = items.get(id);
        if (cartItem!=null){
            //更新商品数量
            cartItem.setCounts(counts);
            //更新商品总金额
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCounts())));
        }
    }

    /**
     * 清空购物车
     */
    public void clearCart(){
        items.clear();
    }

    public Integer getTotalCounts() {
        Integer totalCounts=0;
        for (Map.Entry<Integer,CartItem> entry: items.entrySet()){
            totalCounts+=entry.getValue().getCounts();
        }

        return totalCounts;
    }

    public BigDecimal getTotalPrice() {
        //BigDecimal类型的数据相加不能直接用+，要用add()方法；Integer可以直接相加+
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry: items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCounts=" + getTotalCounts() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}