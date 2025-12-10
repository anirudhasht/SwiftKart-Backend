package com.swiftkart.order_service.controller;

import com.swiftkart.order_service.dto.OrderRequestDto;
import com.swiftkart.order_service.models.Order;
import com.swiftkart.order_service.models.OrderItem;

import com.swiftkart.order_service.repository.OrderRepo;
import com.swiftkart.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class orderController {
    @Autowired
    OrderRepo repo;
    @Autowired
    OrderService service;
    // API for creating an order

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderDto) {
        Order order = service.createOrder(orderDto);
        return ResponseEntity.ok(order);
    }

    //API to fetch all orders

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>>getAllOrders(){
        List<Order>list=repo.findAll();
        return ResponseEntity.ok(list);
    }
    //API to fetch the orders of a particular user

    @GetMapping("/getOrderById/{userId}")
    public ResponseEntity<List<Order>>getOrderById(@PathVariable  int userId){
        List<Order> fetchedOrders = repo.findByUserId(userId);
        if (fetchedOrders.isEmpty()) {
            throw new RuntimeException("No orders found for userId: " + userId);
        }
        return ResponseEntity.ok(fetchedOrders);

    }

 // API to delete order  by Id
    @DeleteMapping("/deleteOrder/{userId}")
    public ResponseEntity<Void>deleteOrderByUserId(@PathVariable int userId){
        repo.deleteByUserId(userId);
        return ResponseEntity.noContent().build();

    }


}
