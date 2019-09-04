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
@RabbitListener(queues = RabbitConfig.deadQueueName)
public class MQDeadKetterConsumer {

    @RabbitHandler
    public void process(String hello, Channel channel, Message message) throws IOException {
        System.out.println("死信队列被调用了--hello="+hello);
    }
}
