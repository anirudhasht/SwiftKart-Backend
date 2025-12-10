package com.swiftkart.inventory_service.kafka;


import com.swiftkart.inventory_service.DTOs.OrderDTO;
import com.swiftkart.inventory_service.DTOs.OrderEventDTO;
import com.swiftkart.inventory_service.models.ReservationItem;
import com.swiftkart.inventory_service.models.ReservationRequest;
import com.swiftkart.inventory_service.service.InventService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryConsumer {

    private final InventService inventService;

    public InventoryConsumer(InventService inventService) {
        this.inventService = inventService;
    }

    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consume(OrderEventDTO event) {
        try {
            OrderDTO order = event.getOrder();


            if (inventService.isOrderProcessed(order.getId())) {
                System.out.println("SKIPPING: Order ID " + order.getId() + " already processed.");
                return;
            }

            // Build reservation request
            ReservationRequest request = new ReservationRequest();
            request.setOrderId(order.getId());
            List<ReservationItem> items = order.getItems().stream()
                    .map(i -> {
                        ReservationItem r = new ReservationItem();
                        r.setProdId(i.getProdId());
                        r.setQuantity(i.getQuantity());
                        return r;
                    })
                    .collect(Collectors.toList());
            request.setItems(items);

            // Reserve stock
            inventService.reserveStock(request);
            System.out.println("SUCCESS: Stock reserved for Order ID " + order.getId());

        } catch (Exception e) {
            System.err.println("FAILED to process order-event: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
