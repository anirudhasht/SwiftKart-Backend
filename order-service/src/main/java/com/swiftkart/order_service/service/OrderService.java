package com.swiftkart.order_service.service;

import org.springframework.stereotype.Service;


import com.swiftkart.order_service.dto.OrderRequestDto;
import com.swiftkart.order_service.kafka.OrderEvent;
import com.swiftkart.order_service.kafka.OrderProducer;
import com.swiftkart.order_service.models.Order;
import com.swiftkart.order_service.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class OrderService {

    @Autowired
    private OrderRepo repo;

    @Autowired
    private OrderProducer producer;

    public Order createOrder(OrderRequestDto orderDto) {

        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setUserName(orderDto.getUserName());
        order.setItems(orderDto.getItems());

        Order saved = repo.save(order);

//        // Send Kafka event
//        OrderEvent event = new OrderEvent(
//                "Order created",
//                "CREATED",
//                saved
//        );
        OrderEvent event = new OrderEvent();
        event.setMessage("Order created");
        event.setStatus("CREATED");
        event.setOrder(saved);

        producer.sendTopic(event);

        return saved;
    }

}
