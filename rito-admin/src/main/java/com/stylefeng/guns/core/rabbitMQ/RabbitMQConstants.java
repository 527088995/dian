package com.stylefeng.guns.core.rabbitMQ;

/**
 * @author ds
 */
public class RabbitMQConstants {

    /**
     * 定义死信队列转发队列
     */
    public static final String ORDER_REDIRECT_QUEUE_NAME = "order.redirect.queue";

    /**
     * 功能描述: 死信队列
     */
    public static final String ORDER_DEAD_KEY = "dead.key";
    public static final String ORDER_DEAD_QUEUE_NAME = "order.dead.queue";
    public static final String ORDER_DEAD_EXCHANGE_NAME = "order.dead.exchange";
    public static final String ORDER_DEAD_ROUTING_KEY = "order.dead";

    /**
     * 订单结算MQ队列
     */
    public static final String ORDER_SETTLEMENT_QUEUE = "order.settlement.queue";
    /**
     * 订单结算MQ通道
     */
    public static final String ORDER_SETTLEMENT_EXCHANGE = "order.settlement.exchange";
    /**
     * 订单结算MQ KEY
     */
    public static final String ORDER_SETTLEMENT_KEY = "order.settlement.key";

    /**
     * 用户订单总表-订单创建消息
     */
    public static final String ORDER_SUMMARY_QUEUE = "dian.order.summary.queue";
    public static final String ORDER_SUMMARY_EXCHANGE = "dian.order.summary.exchange";
    public static final String ORDER_SUMMARY_ROUTINGKEY = "dian.order.summary.routingKey";

}
