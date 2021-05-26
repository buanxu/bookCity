package com.bookcity.test;

import com.bookcity.entity.Cart;
import com.bookcity.entity.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"白夜行",2,new BigDecimal(50),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"嫌疑人x的献身",1,new BigDecimal(49),new BigDecimal(98)));
        cart.addItem(new CartItem(3,"追风筝的人",2,new BigDecimal(38),new BigDecimal(76)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"白夜行",2,new BigDecimal(50),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"嫌疑人x的献身",1,new BigDecimal(49),new BigDecimal(98)));
        cart.addItem(new CartItem(3,"追风筝的人",2,new BigDecimal(38),new BigDecimal(76)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void updateItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"白夜行",2,new BigDecimal(50),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"嫌疑人x的献身",1,new BigDecimal(49),new BigDecimal(98)));
        cart.addItem(new CartItem(3,"追风筝的人",2,new BigDecimal(38),new BigDecimal(76)));
        cart.updateItem(1, 1);
        System.out.println(cart);
    }

    @Test
    public void clearCart() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"白夜行",2,new BigDecimal(50),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"嫌疑人x的献身",1,new BigDecimal(49),new BigDecimal(98)));
        cart.addItem(new CartItem(3,"追风筝的人",2,new BigDecimal(38),new BigDecimal(76)));
        cart.clearCart();
        System.out.println(cart);
    }
}