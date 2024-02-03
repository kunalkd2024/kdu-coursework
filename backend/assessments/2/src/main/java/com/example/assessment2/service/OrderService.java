package com.example.assessment2.service;

import com.example.assessment2.dao.OrderDAO;
import com.example.assessment2.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    OrderDAO orderDAO;
    @Autowired
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    public void addOrder(Order order){
        orderDAO.addOrder(order);
    }

    public void deleteOrder(Order order){
        orderDAO.deleteOrder(order);
    }

    public List<Order> getAllOrders(){
        return orderDAO.getAllOrders();
    }

    public Order getOrderById(int id){
        return orderDAO.getOrderByIndex(id);
    }

    public Order getOrder(int id){
        for(Order u : OrderDAO.getAllOrders()){
            if(u.getId().equals(id)){
                return u;
            }
        }
        return null;
    }
}
