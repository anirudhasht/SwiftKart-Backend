package com.swiftkart.inventory_service.service;

import com.swiftkart.inventory_service.models.*;
import com.swiftkart.inventory_service.repository.InventoryRepositiry;
import com.swiftkart.inventory_service.repository.InventoryServiceRepo;
import com.swiftkart.inventory_service.service.ProductAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import java.util.Optional;

@Service
public class InventService {
    @Autowired
    private InventoryRepositiry prodRepo;

    private InventoryServiceRepo orderRepo;

    // Constructor Injection
    InventService(InventoryRepositiry prodRep, InventoryServiceRepo orderRepo){
        this.prodRepo=prodRep;
        this.orderRepo=orderRepo;
    }


    @Transactional
    public boolean reserveStock( ReservationRequest request){

        if(orderRepo.findByOrderId(request.getOrderId()).isPresent()){

            System.out.println("LOG: Order ID " + request.getOrderId() + " already processed.");
            return true;
        }

        try {

            for(ReservationItem item: request.getItems()){
                Optional<Inventory> inventoryOpt = prodRepo.findByProdId(item.getProdId());


                if (!inventoryOpt.isPresent()) {
                    throw new RuntimeException("Product's not found in inventory: " + item.getProdId());
                }

                Inventory inventory = inventoryOpt.get();
                int requiredQuantity = item.getQuantity();


                if (inventory.getAvailableStock() < requiredQuantity) {
                    throw new RuntimeException("Insufficient stock for product: " + item.getProdId() +
                            ". Available: " + inventory.getAvailableStock() +
                            ", Required: " + requiredQuantity);
                }


                inventory.setReservedQuantity(inventory.getReservedQuantity() + requiredQuantity);
                prodRepo.save(inventory);


                InventoryService reservation = new InventoryService();
                reservation.setOrderId(request.getOrderId());
                reservation.setProdId(item.getProdId());
                reservation.setQuantity(requiredQuantity);
                orderRepo.save(reservation);
            }


            return true;

        } catch (ObjectOptimisticLockingFailureException e) {

            System.err.println("Optimistic Locking Failure: Retrying order " + request.getOrderId() + " may be required.");
            throw new RuntimeException("Inventory update conflict. Please retry reservation.", e);
        } catch (RuntimeException e) {

            throw e;
        } catch (Exception e) {

            System.err.println("FATAL DB ERROR: Transaction rolled back for Order ID " + request.getOrderId() + ". Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to commit reservation due to an unexpected database constraint or connectivity issue.", e);
        }
    }


    public void addProduct(ProductRequest prodReq) {
        Long prodId = prodReq.getProdId();

        if (prodRepo.existsByProdId(prodId)) {
            throw new ProductAlreadyExistsException(prodId);
        }

        Inventory inventory = new Inventory();
        inventory.setProdId(prodId);
        inventory.setStockQuantity(prodReq.getInitialStock());
        inventory.setReservedQuantity(0);

        prodRepo.save(inventory);
    }


    @Transactional
    public Inventory addStock(Long productId, int quantity) {
        Optional<Inventory> inventoryOpt = prodRepo.findByProdId(productId);

        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();

            inventory.setReservedQuantity(inventory.getReservedQuantity() + quantity);
            return prodRepo.save(inventory);
        } else {
            Inventory newInventory = new Inventory();
            newInventory.setProdId(productId);
            newInventory.setStockQuantity(quantity);
            return prodRepo.save(newInventory);
        }
    }
    public boolean isOrderProcessed(long  productId) {
        return prodRepo.existsByProdId(productId);
    }
}