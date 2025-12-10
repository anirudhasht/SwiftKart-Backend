package com.swiftkart.order_service.repository;

import com.swiftkart.order_service.models.Order;
//import com.swiftkart.order_service.models.orders;
import jakarta.transaction.Transactional;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  OrderRepo extends JpaRepository<Order,Integer> {

   List<Order> findByUserId(int userId);
   @Transactional
   @Modifying
   void deleteByUserId(int userId);

}
