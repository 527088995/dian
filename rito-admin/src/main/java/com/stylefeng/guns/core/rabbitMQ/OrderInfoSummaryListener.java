package com.stylefeng.guns.core.rabbitMQ;

import com.stylefeng.guns.core.util.JacksonUtil;
import com.stylefeng.guns.modular.system.model.Notice;
import com.stylefeng.guns.modular.system.service.INoticeService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: xqc
 * @Date: 2019/3/7 09:35
 * @Description: 订单汇总
 */
@Component
public class OrderInfoSummaryListener {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("OrderInfoSummaryListener");

    @Autowired
    private INoticeService noticeService;

    /**
     * 功能描述 消费正常队列
     *
     * @param s
     * @param message
     * @return void
     * @author wj
     * @date 2019/3/12 15:33
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void receiveMessage(String s, Message message) {
        try {
            log.info(">>>>>>>>>>>>>>消费正常队列收到订单汇总表MQ" + s);
            log.info("============" + new String(message.getBody()) + "==============");

            String str = new String(message.getBody());
            Notice oo = JacksonUtil.fromJson(str, Notice.class);
            if (oo == null) {
                log.info(">>>>>>>>>>>>>>正常队列订单汇总MQ消费异常：订单参数异常");
                return;
            }

            boolean rb = noticeService.insert(oo);
            if (!rb) {
                log.info(">>>>>>>>>>>>>>正常队列订单汇总MQ消费异常：订单参数异常");
            }
        } catch (Exception e) {
            log.error("RabbitMQ Error 正常队列订单汇总MQ消费" + e.getMessage(), e);
        }
    }


}
