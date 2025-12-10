package com.swiftkart.inventory_service.service;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(Long prodId) {
        super("Product ID " + prodId + " already exists in inventory.");
    }
}
