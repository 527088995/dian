package com.stylefeng.guns.modular.toLoan.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.DelFlag;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.page.LayuiPageInfo;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import com.stylefeng.guns.modular.system.warpper.NoticeWrapper;
import com.stylefeng.guns.modular.toLoan.model.EntAcctInfoFb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.toLoan.service.IEntAcctInfoFbService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 企业借贷账户信息流水表控制器
 *
 * @author wj
 * @Date 2019-06-11 10:59:37
 */
@Controller
@RequestMapping("/entAcctInfoFb")
public class EntAcctInfoFbController extends BaseController {

    private String PREFIX = "/modular/toLoan/entAcctInfoFb/";

    @Autowired
    private IEntAcctInfoFbService entAcctInfoFbService;

    /**
     * 跳转到企业借贷账户信息流水表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "entAcctInfoFb.html";
    }

    /**
     * 跳转到添加企业借贷账户信息流水表
     */
    @RequestMapping(value = "/entAcctInfoFb_add")
    public String entAcctInfoFbAdd() {
        return PREFIX + "entAcctInfoFb_add.html";
    }

    /**
     * 跳转到修改企业借贷账户信息流水表
     */
    @RequestMapping(value = "/entAcctInfoFb_update", method = RequestMethod.GET)
    public String entAcctInfoFbUpdate(String entAcctInfoFbId, Model model) {
        EntAcctInfoFb entAcctInfoFb = entAcctInfoFbService.selectById(entAcctInfoFbId);
        model.addAllAttributes(BeanUtil.beanToMap(entAcctInfoFb));
        LogObjectHolder.me().set(entAcctInfoFb);
        return PREFIX + "entAcctInfoFb_edit.html";
    }

    /**
     * 获取企业借贷账户信息流水表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LayuiPageInfo list(String condition) {
        LayuiPageInfo list = this.entAcctInfoFbService.list();
        return list;
    }

    /**
     * 新增企业借贷账户信息流水表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(EntAcctInfoFb entAcctInfoFb) {
    if (ToolUtil.isOneEmpty(entAcctInfoFb)) {
                throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
            }
        entAcctInfoFb.setCreateUser(ShiroKit.getUserNotNull().getId());
        entAcctInfoFb.setCreateTime(new Date());
        entAcctInfoFb.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.entAcctInfoFbService.insert(entAcctInfoFb);
    return SUCCESS_TIP;
    }

    /**
     * 删除企业借贷账户信息流水表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam Long entAcctInfoFbId) {
    EntAcctInfoFb entAcctInfoFb = new EntAcctInfoFb();
    entAcctInfoFb.setDeleteFlag(DelFlag.DELETED.getCode());
    entAcctInfoFb.setId(entAcctInfoFbId);
        entAcctInfoFbService.updateById(entAcctInfoFb);
        return SUCCESS_TIP;
    }

    /**
     * 修改企业借贷账户信息流水表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(EntAcctInfoFb entAcctInfoFb) {
            entAcctInfoFb.setUpdateUser(ShiroKit.getUserNotNull().getId());
            entAcctInfoFb.setUpdateTime(new Date());
        entAcctInfoFbService.updateById(entAcctInfoFb);
        return SUCCESS_TIP;
    }

    /**
     * 企业借贷账户信息流水表详情
     */
    @RequestMapping(value = "/detail/{entAcctInfoFbId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("entAcctInfoFbId") Integer entAcctInfoFbId) {
        return entAcctInfoFbService.selectById(entAcctInfoFbId);
    }
}
