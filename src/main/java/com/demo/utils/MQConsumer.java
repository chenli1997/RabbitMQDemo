package com.demo.utils;

import com.demo.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author chen
 * @date 2019/7/25 13:24
 */
@Component
@RabbitListener(queues = RabbitConfig.FANOUT_MSG)
public class MQConsumer {

    @RabbitHandler
    public void process(String hello, Channel channel, Message message) throws IOException, InterruptedException {
        if (hello.equals("1111")) {
            //消费成功
            System.out.println("receiver success :  " + hello);
        }else {
            System.out.println("receiver error :  " + hello);
            throw new RuntimeException("sad");
        }
    }
}
