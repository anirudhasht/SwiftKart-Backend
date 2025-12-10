package com.swiftkart.inventory_service.models;



import lombok.Data;
import lombok.NonNull;
@Data
public class ProductRequest {

    @NonNull
    private Long prodId;

    @NonNull

    private String name;


    private int initialStock;


    private double price;



    public Long getProdId() { return prodId; }
    public void setProdId(Long prodId) { this.prodId = prodId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getInitialStock() { return initialStock; }
    public void setInitialStock(int initialStock) { this.initialStock = initialStock; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
