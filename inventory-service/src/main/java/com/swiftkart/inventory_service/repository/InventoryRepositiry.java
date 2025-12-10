package com.swiftkart.inventory_service.repository;

import com.swiftkart.inventory_service.models.Inventory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

public interface InventoryRepositiry extends JpaRepository<Inventory,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    Optional<Inventory> findByProdId(Long prodId);

    boolean existsByProdId(Long prodId);


}
