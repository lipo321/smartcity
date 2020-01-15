package com.smartcity.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lipoGiser
 * @date 2020/1/15 15:25
 * @Version 0.1
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiver {
    @RabbitHandler
    public void process(Map testMessage){
        System.out.println("DirectReceiver消費者收到消息："+testMessage.toString());
    }
}
