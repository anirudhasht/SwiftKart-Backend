package com.swiftkart.inventory_service.models;

import lombok.Data;

@Data
public class ReservationItem {
    private long  prodId;
    private int quantity;

    public long  getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
