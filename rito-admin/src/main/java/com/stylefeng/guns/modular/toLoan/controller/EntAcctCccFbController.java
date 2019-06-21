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
import com.stylefeng.guns.modular.toLoan.model.EntAcctCccFb;
import com.stylefeng.guns.modular.toLoan.service.IEntAcctCccFbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 企业借贷-抵质押物信息流水表控制器
 *
 * @author wj
 * @Date 2019-06-12 11:14:18
 */
@Controller
@RequestMapping("/entAcctCccFb")
public class EntAcctCccFbController extends BaseController {

    private String PREFIX = "/modular/toLoan/entAcctCccFb/";

    @Autowired
    private IEntAcctCccFbService entAcctCccFbService;

    /**
     * 跳转到企业借贷-抵质押物信息流水表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "entAcctCccFb.html";
    }

    /**
     * 跳转到添加企业借贷-抵质押物信息流水表
     */
    @RequestMapping(value = "/entAcctCccFb_add")
    public String entAcctCccFbAdd() {
        return PREFIX + "entAcctCccFb_add.html";
    }

    /**
     * 跳转到修改企业借贷-抵质押物信息流水表
     */
    @RequestMapping(value = "/entAcctCccFb_update", method = RequestMethod.GET)
    public String entAcctCccFbUpdate(Long entAcctCccFbId, Model model) {
        EntAcctCccFb entAcctCccFb = entAcctCccFbService.selectById(entAcctCccFbId);
        model.addAllAttributes(BeanUtil.beanToMap(entAcctCccFb));
        LogObjectHolder.me().set(entAcctCccFb);
        return PREFIX + "entAcctCccFb_edit.html";
    }

    /**
     * 获取企业借贷-抵质押物信息流水表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.entAcctCccFbService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增企业借贷-抵质押物信息流水表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(EntAcctCccFb entAcctCccFb) {
        if (ToolUtil.isOneEmpty(entAcctCccFb)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        entAcctCccFb.setCreateUser(ShiroKit.getUserNotNull().getId());
        entAcctCccFb.setCreateTime(new Date());
        entAcctCccFb.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.entAcctCccFbService.insert(entAcctCccFb);
        return SUCCESS_TIP;
    }

    /**
     * 删除企业借贷-抵质押物信息流水表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam Long entAcctCccFbId) {
        EntAcctCccFb entAcctCccFb = new EntAcctCccFb();
        entAcctCccFb.setDeleteFlag(DelFlag.DELETED.getCode());
        entAcctCccFb.setEntAcctId(entAcctCccFbId);
        entAcctCccFbService.updateById(entAcctCccFb);
        return SUCCESS_TIP;
    }

    /**
     * 修改企业借贷-抵质押物信息流水表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(EntAcctCccFb entAcctCccFb) {
        entAcctCccFb.setUpdateUser(ShiroKit.getUserNotNull().getId());
        entAcctCccFb.setUpdateTime(new Date());
        entAcctCccFbService.updateById(entAcctCccFb);
        return SUCCESS_TIP;
    }

    /**
     * 企业借贷-抵质押物信息流水表详情
     */
    @RequestMapping(value = "/detail/{entAcctCccFbId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("entAcctCccFbId") Integer entAcctCccFbId) {
        return entAcctCccFbService.selectById(entAcctCccFbId);
    }
}
