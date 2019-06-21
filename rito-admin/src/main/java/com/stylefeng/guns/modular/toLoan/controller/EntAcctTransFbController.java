package com.stylefeng.guns.modular.toLoan.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.DelFlag;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import com.stylefeng.guns.modular.toLoan.model.EntAcctTransFb;
import com.stylefeng.guns.modular.toLoan.service.IEntAcctTransFbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 企业借贷-特定交易信息流水表控制器
 *
 * @author wj
 * @Date 2019-06-12 11:08:08
 */
@Controller
@RequestMapping("/entAcctTransFb")
public class EntAcctTransFbController extends BaseController {

    private String PREFIX = "/modular/toLoan/entAcctTransFb/";

    @Autowired
    private IEntAcctTransFbService entAcctTransFbService;

    /**
     * 跳转到企业借贷-特定交易信息流水表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "entAcctTransFb.html";
    }

    /**
     * 跳转到添加企业借贷-特定交易信息流水表
     */
    @RequestMapping(value = "/entAcctTransFb_add")
    public String entAcctTransFbAdd() {
        return PREFIX + "entAcctTransFb_add.html";
    }

    /**
     * 跳转到修改企业借贷-特定交易信息流水表
     */
    @RequestMapping(value = "/entAcctTransFb_update", method = RequestMethod.GET)
    public String entAcctTransFbUpdate(Long entAcctTransFbId, Model model) {
        EntAcctTransFb entAcctTransFb = entAcctTransFbService.selectById(entAcctTransFbId);
        model.addAllAttributes(BeanUtil.beanToMap(entAcctTransFb));
        LogObjectHolder.me().set(entAcctTransFb);
        return PREFIX + "entAcctTransFb_edit.html";
    }

    /**
     * 获取企业借贷-特定交易信息流水表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.entAcctTransFbService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增企业借贷-特定交易信息流水表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(EntAcctTransFb entAcctTransFb) {
        if (ToolUtil.isOneEmpty(entAcctTransFb)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        entAcctTransFb.setCreateUser(ShiroKit.getUserNotNull().getId());
        entAcctTransFb.setCreateTime(new Date());
        entAcctTransFb.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.entAcctTransFbService.insert(entAcctTransFb);
        return SUCCESS_TIP;
    }

    /**
     * 删除企业借贷-特定交易信息流水表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam Long entAcctTransFbId) {
        EntAcctTransFb entAcctTransFb = new EntAcctTransFb();
        entAcctTransFb.setDeleteFlag(DelFlag.DELETED.getCode());
        entAcctTransFb.setId(entAcctTransFbId);
        entAcctTransFbService.updateById(entAcctTransFb);
        return SUCCESS_TIP;
    }

    /**
     * 修改企业借贷-特定交易信息流水表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(EntAcctTransFb entAcctTransFb) {
        entAcctTransFb.setUpdateUser(ShiroKit.getUserNotNull().getId());
        entAcctTransFb.setUpdateTime(new Date());
        entAcctTransFbService.updateById(entAcctTransFb);
        return SUCCESS_TIP;
    }

    /**
     * 企业借贷-特定交易信息流水表详情
     */
    @RequestMapping(value = "/detail/{entAcctTransFbId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("entAcctTransFbId") Integer entAcctTransFbId) {
        return entAcctTransFbService.selectById(entAcctTransFbId);
    }
}
