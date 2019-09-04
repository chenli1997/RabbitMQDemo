package com.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen
 * @date 2019/7/25 13:15
 */
@Configuration
public class RabbitConfig {
    /**
     * 定义死信队列相关信息
     */
    public final static String deadQueueName = "dead_queue";
    public final static String deadRoutingKey = "dead_routing_key";
    public final static String deadExchangeName = "dead_exchange";
    /**
     * 死信队列 交换机标识符
     */
    public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";


    public static final String FANOUT_MSG = "fanout.msg";
    public static final String FANOUT_EXCHANGE_MSG = "fanout.exchange";

    @Bean
    public Queue msgQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put(DEAD_LETTER_QUEUE_KEY, deadExchangeName);
        args.put(DEAD_LETTER_ROUTING_KEY, deadRoutingKey);
        Queue queue = new Queue(FANOUT_MSG, true, false, false, args);;
        return queue;
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_MSG);
    }

    @Bean
    Binding bindingExchangeMsg(Queue msgQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(msgQueue).to(fanoutExchange);
    }

    /**
     * 配置死信队列
     *
     * @return
     */
    @Bean
    public Queue deadQueue() {
        Queue queue = new Queue(deadQueueName, true);
        return queue;
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(deadExchangeName,true,false);
    }

    @Bean
    public Binding bindingDeadExchange(Queue deadQueue, DirectExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(deadRoutingKey);
    }
}
