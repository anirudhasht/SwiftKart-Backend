package com.swiftkart.inventory_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="inventory",uniqueConstraints = @UniqueConstraint(columnNames = "productId"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Long  prodId;
    @Column(nullable = false)
    private int stockQuantity;

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    @Column(nullable = false)
    private int reservedQuantity=0;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long  getProdId() {
        return prodId;
    }

    public void setProdId(Long  prodId) {
        this.prodId = prodId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Version
    private Long version;

    public int getAvailableStock(){
        return this.stockQuantity-reservedQuantity;
    }
}

