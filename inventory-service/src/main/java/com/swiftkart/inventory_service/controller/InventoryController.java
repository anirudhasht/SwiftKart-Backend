package com.swiftkart.inventory_service.controller;

import com.swiftkart.inventory_service.models.ProductRequest;
import com.swiftkart.inventory_service.models.ReservationRequest;
import com.swiftkart.inventory_service.service.InventService;
import com.swiftkart.inventory_service.service.ProductAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventService service;
   @PostMapping("/stock")
    public ResponseEntity<Void>reserveStock(@RequestBody ReservationRequest request){
        try{
            boolean ans=service.reserveStock(request);

            if(ans){
                return ResponseEntity.ok().build();
            }
            else {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        catch(RuntimeException e){
            System.err.println("Reservation Failed for Order ID " + request.getOrderId() + ": " + e.getMessage());



        throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
    }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Void> addProduct(@RequestBody ProductRequest prodReq){
        try{

            service.addProduct(prodReq);


            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch(ProductAlreadyExistsException e){
            System.err.println("Product addition failed: " + e.getMessage());


            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
        catch(RuntimeException e){
            System.err.println("Product addition failed with unexpected error: " + e.getMessage());


            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add product.", e);
        }
    }

}
