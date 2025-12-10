package com.swiftkart.inventory_service.DTOs;


public class OrderItemDTO {
    private Long prodId;
    private int quantity;

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
