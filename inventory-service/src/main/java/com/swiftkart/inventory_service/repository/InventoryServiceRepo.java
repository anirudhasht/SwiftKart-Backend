package com.swiftkart.inventory_service.repository;

import com.swiftkart.inventory_service.models.InventoryService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryServiceRepo extends JpaRepository<InventoryService,Long>{
    Optional<InventoryServiceRepo> findByOrderId(long  orderId);
}
