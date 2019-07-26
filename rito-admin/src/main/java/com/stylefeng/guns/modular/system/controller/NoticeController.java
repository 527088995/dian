/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stylefeng.guns.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.longtop.efmp.peg.srv.bs.IAppSrvBS;
import com.longtop.efmp.peg.xmlvo.PegHeadVO;
import com.longtop.efmp.peg.xmlvo.cas.Cas010001VO;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import com.stylefeng.guns.core.common.constant.dictmap.NoticeMap;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.rabbitMQ.RabbitConfig;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.JacksonUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Notice;
import com.stylefeng.guns.modular.system.service.INoticeService;
import com.stylefeng.guns.modular.system.warpper.NoticeWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知控制器
 *
 * @author ...
 * @Date 2017-05-09 23:02:21
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    private String PREFIX = "/modular/system/notice/";

    @Autowired
    private INoticeService noticeService;


    @Autowired
    private IAppSrvBS iAppSrvBS;

    @Resource
    private RabbitTemplate rabbitTemplate;

    private com.longtop.efmp.peg.bas.util.PegHttpInvokerRequestExecutor PegHttpInvokerRequestExecutor;

    /**
     * 跳转到通知列表首页
     *
     * @author ...
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "notice.html";
    }

    /**
     * 跳转到添加通知
     *
     * @author ...
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/notice_add")
    public String noticeAdd() {
        return PREFIX + "notice_add.html";
    }

    /**
     * 跳转到修改通知
     *
     * @author ...
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/notice_update/{noticeId}")
    public String noticeUpdate(@PathVariable Long noticeId, Model model) {
        Notice notice = this.noticeService.selectById(noticeId);
        model.addAllAttributes(BeanUtil.beanToMap(notice));
        LogObjectHolder.me().set(notice);
        return PREFIX + "notice_edit.html";
    }

    /**
     * 发送消息
     *
     * @param exchange   交换机名称
     * @param routingKey 路由key
     * @param message    消息内容
     * @throws AmqpException
     */
    private void convertAndSend(String exchange, String routingKey, final Object message) throws AmqpException {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        } catch (Exception e) {
            logger.error("MQ消息发送异常，消息ID：{}，消息体:{}, exchangeName:{}, routingKey:{}",
                    JSON.toJSONString(message), exchange, routingKey, e);
            // TODO 保存消息到数据库
        }
    }

    /**
     * 获取通知列表
     *
     * @author ...
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.noticeService.list(page, condition);

        Cas010001VO vo = new Cas010001VO();
        PegHeadVO pegHeadVO = new PegHeadVO();
//		pegHeadVO.setMesgId("mesgid0000001");
//		pegHeadVO.setMesgRefId("mesgrefid0001");
        //公共信息
        vo.setMsgIdId("msgIdCas010001005");//每个报文都必须不一样
        vo.setMsgIdCreDtTm("20180201123456");
        vo.setOrigReceiver("111111111");

        //各个报文信息
        vo.setOrgnlMsgIdId("222223");
        BigDecimal bal = new BigDecimal(30000);
        BigDecimal curbal = new BigDecimal(28000);
        vo.setBal(bal);
        vo.setCurBal(curbal);
        vo.setCpeAcctNo("22222222");
        vo.setAcctSts("STT000");
        vo.setBizCtrlInfPrcCd("1230I000000");

//        PegResultVO resultVO = iAppSrvBS.doRequest("COR", "uniqueCas010001005", pegHeadVO, vo);//第二个参数必须唯一
//        System.out.println("CAS010001成功");
//        System.out.println("结果标识:" + resultVO.getResultFlag());
//        System.out.println("结果码:" + resultVO.getResultCode());
//        System.out.println("结果消息:" + resultVO.getResultMsg());


        page.setRecords(new NoticeWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增通知
     *
     * @author ...
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "新增通知", key = "title", dict = NoticeMap.class)
    public Object add(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getTitle(), notice.getContent())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        notice.setCreateUser(ShiroKit.getUserNotNull().getId());
        notice.setCreateTime(new Date());
        this.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, JacksonUtil.toJson(notice));

        // this.noticeService.insert(notice);
        return SUCCESS_TIP;
    }

    /**
     * 删除通知
     *
     * @author ...
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除通知", key = "noticeId", dict = DeleteDict.class)
    public Object delete(@RequestParam Long noticeId) {

        //缓存通知名称
        LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(noticeId));

        this.noticeService.deleteById(noticeId);

        return SUCCESS_TIP;
    }

    /**
     * 修改通知
     *
     * @author ...
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改通知", key = "title", dict = NoticeMap.class)
    public Object update(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getNoticeId(), notice.getTitle(), notice.getContent())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Notice old = this.noticeService.selectById(notice.getNoticeId());
        old.setTitle(notice.getTitle());
        old.setContent(notice.getContent());
        old.setUpdateUser(ShiroKit.getUser().getId());
        old.setUpdateTime(new Date());
        this.noticeService.updateById(old);
        return SUCCESS_TIP;
    }

}
