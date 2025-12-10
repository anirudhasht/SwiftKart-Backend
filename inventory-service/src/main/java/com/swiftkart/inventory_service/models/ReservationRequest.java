package com.swiftkart.inventory_service.models;

import lombok.Data;

import java.util.List;

@Data
public class ReservationRequest {

    private long   orderId;

    public long   getOrderId() {
        return orderId;
    }

    public void setOrderId(long  orderId) {
        this.orderId = orderId;
    }

    public List<ReservationItem> getItems() {
        return items;
    }

    public void setItems(List<ReservationItem> items) {
        this.items = items;
    }

    List<ReservationItem> items;
}
