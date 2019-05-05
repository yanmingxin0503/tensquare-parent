package com.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "itcast")
public class Customer {
    @RabbitHandler
    public void showMessage(String message){
        System.out.println("itcast："+message);
    }
}
