package com.example.assessment2.dao;

import com.example.assessment2.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    static List<Order> orderList = null;
    public OrderDAO() {
        orderList = new ArrayList<>();
    }
    public void addOrder(Order order){
        orderList.add(order);
    }

    public void deleteOrder(Order order){
        orderList.remove(order);
    }

    public Order getOrderByIndex(int index){
        return orderList.get(index);
    }

    public static List<Order> getAllOrders(){
        return orderList;
    }

}
