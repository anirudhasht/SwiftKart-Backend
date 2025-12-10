package com.swiftkart.order_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private static final String TOPIC = "order-events";
   @Autowired
    private KafkaTemplate<String,OrderEvent>kafkaTemplate;

   public void sendTopic(OrderEvent event){
       kafkaTemplate.send(TOPIC, event);
       System.out.println("ORDER EVENT SENT TO KAFKA");
   }
}
