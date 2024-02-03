package com.example.assessment2.controller;

import com.example.assessment2.entity.Order;
import com.example.assessment2.entity.User;
import com.example.assessment2.service.OrderService;
import com.example.assessment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> showOrder(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody Order order) {
        orderService.addOrder(order);
        return ResponseEntity.ok("Order placed successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Integer orderId) {
        orderService.deleteOrder(orderService.getOrderById(orderId));
        return ResponseEntity.ok("Order deleted successfully");
    }

    @PutMapping("/update/{orderID}")
    public ResponseEntity<String> updateUser(@PathVariable Integer orderID, @RequestBody Order order) {
        Order oldOrder = orderService.getOrderById(orderID);
        orderService.deleteOrder(oldOrder);
        orderService.addOrder(order);
        return ResponseEntity.ok("Order updated successfully");
    }
}
