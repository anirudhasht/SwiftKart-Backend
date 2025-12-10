package com.swiftkart.order_service.dto; // Create a new 'dto' package

import com.swiftkart.order_service.models.OrderItem;
import lombok.Data;
import java.util.List;


public class OrderRequestDto {

    private int userId;
    private String userName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    private List<OrderItem> items;
}