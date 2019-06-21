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
import com.stylefeng.guns.modular.toLoan.model.EntAcctRltRepymtFb;
import com.stylefeng.guns.modular.toLoan.service.IEntAcctRltRepymtFbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 企业借贷-相关还款人信息流水表控制器
 *
 * @author wj
 * @Date 2019-06-11 15:31:33
 */
@Controller
@RequestMapping("/entAcctRltRepymtFb")
public class EntAcctRltRepymtFbController extends BaseController {

    private String PREFIX = "/modular/toLoan/entAcctRltRepymtFb/";

    @Autowired
    private IEntAcctRltRepymtFbService entAcctRltRepymtFbService;

    /**
     * 跳转到企业借贷-相关还款人信息流水表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "entAcctRltRepymtFb.html";
    }

    /**
     * 跳转到添加企业借贷-相关还款人信息流水表
     */
    @RequestMapping(value = "/entAcctRltRepymtFb_add")
    public String entAcctRltRepymtFbAdd() {
        return PREFIX + "entAcctRltRepymtFb_add.html";
    }

    /**
     * 跳转到修改企业借贷-相关还款人信息流水表
     */
    @RequestMapping(value = "/entAcctRltRepymtFb_update", method = RequestMethod.GET)
    public String entAcctRltRepymtFbUpdate(String entAcctRltRepymtFbId, Model model) {
        EntAcctRltRepymtFb entAcctRltRepymtFb = entAcctRltRepymtFbService.selectById(entAcctRltRepymtFbId);
        model.addAllAttributes(BeanUtil.beanToMap(entAcctRltRepymtFb));
        LogObjectHolder.me().set(entAcctRltRepymtFb);
        return PREFIX + "entAcctRltRepymtFb_edit.html";
    }

    /**
     * 获取企业借贷-相关还款人信息流水表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.entAcctRltRepymtFbService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增企业借贷-相关还款人信息流水表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(EntAcctRltRepymtFb entAcctRltRepymtFb) {
        if (ToolUtil.isOneEmpty(entAcctRltRepymtFb)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        entAcctRltRepymtFb.setCreateUser(ShiroKit.getUserNotNull().getId());
        entAcctRltRepymtFb.setCreateTime(new Date());
        entAcctRltRepymtFb.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.entAcctRltRepymtFbService.insert(entAcctRltRepymtFb);
        return SUCCESS_TIP;
    }

    /**
     * 删除企业借贷-相关还款人信息流水表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam Long entAcctRltRepymtFbId) {
        EntAcctRltRepymtFb entAcctRltRepymtFb = new EntAcctRltRepymtFb();
        entAcctRltRepymtFb.setDeleteFlag(DelFlag.DELETED.getCode());
        entAcctRltRepymtFb.setId(entAcctRltRepymtFbId);
        entAcctRltRepymtFbService.updateById(entAcctRltRepymtFb);
        return SUCCESS_TIP;
    }

    /**
     * 修改企业借贷-相关还款人信息流水表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(EntAcctRltRepymtFb entAcctRltRepymtFb) {
        entAcctRltRepymtFb.setUpdateUser(ShiroKit.getUserNotNull().getId());
        entAcctRltRepymtFb.setUpdateTime(new Date());
        entAcctRltRepymtFbService.updateById(entAcctRltRepymtFb);
        return SUCCESS_TIP;
    }

    /**
     * 企业借贷-相关还款人信息流水表详情
     */
    @RequestMapping(value = "/detail/{entAcctRltRepymtFbId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("entAcctRltRepymtFbId") Integer entAcctRltRepymtFbId) {
        return entAcctRltRepymtFbService.selectById(entAcctRltRepymtFbId);
    }
}
