package com.stylefeng.guns.core.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 创建队列和交换机
 *
 * @author suntf
 * @date 2019/3/7 9:30
 */
@Configuration
public class RabbitMQConfig {

    //创建queue
    @Bean("paymentNotifyQueue")
    public Queue paymentNotifyQueue() {
        return QueueBuilder.durable("notify.payment").build();
    }

    @Bean("debugQueue")
    public Queue debugQueue() {
        return new Queue("zhihao.debug");
    }

    /**
     * 死信队列跟交换机类型没有关系 不一定为directExchange  不影响该类型交换机的特性.
     *
     * @return the exchange
     */
    @Bean("deadLetterExchange")
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange(RabbitMQConstants.ORDER_DEAD_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 声明一个死信队列.
     * x-dead-letter-exchange   对应  死信交换机
     * x-dead-letter-routing-key  对应 死信队列
     *
     * @return the queue
     */
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    声明  死信交换机
        args.put("x-dead-letter-exchange", RabbitMQConstants.ORDER_DEAD_EXCHANGE_NAME);
//       x-dead-letter-routing-key    声明 死信路由键
        args.put("x-dead-letter-routing-key", RabbitMQConstants.ORDER_DEAD_KEY);
        return QueueBuilder.durable(RabbitMQConstants.ORDER_DEAD_QUEUE_NAME).withArguments(args).build();
    }

    /**
     * 定义死信队列转发队列.
     *
     * @return the queue
     */
    @Bean("redirectQueue")
    public Queue redirectQueue() {
        return QueueBuilder.durable(RabbitMQConstants.ORDER_REDIRECT_QUEUE_NAME).build();
    }

    /**
     * 死信路由通过 DL_KEY 绑定键绑定到死信队列上.ORDER_DEAD_ROUTING_KEY
     *
     * @return the binding
     */
    @Bean
    public Binding deadLetterBinding() {
        return new Binding(RabbitMQConstants.ORDER_DEAD_QUEUE_NAME, Binding.DestinationType.QUEUE, RabbitMQConstants.ORDER_DEAD_EXCHANGE_NAME, RabbitMQConstants.ORDER_DEAD_ROUTING_KEY, null);

    }

    /**
     * 死信路由通过 KEY_R 绑定键绑定到死信队列上.
     *
     * @return the binding
     */
    @Bean
    public Binding redirectBinding() {
        return new Binding(RabbitMQConstants.ORDER_REDIRECT_QUEUE_NAME, Binding.DestinationType.QUEUE, RabbitMQConstants.ORDER_DEAD_EXCHANGE_NAME, RabbitMQConstants.ORDER_DEAD_KEY, null);
    }


}
