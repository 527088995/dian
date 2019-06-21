package com.stylefeng.guns.modular.flowable.controller;

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
import com.stylefeng.guns.modular.flowable.model.SysExpense;
import com.stylefeng.guns.modular.flowable.service.ISysExpenseService;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 报销管理-报销表控制器
 *
 * @author wj
 * @Date 2019-06-12 15:17:00
 */
@Controller
@RequestMapping("/sysExpense")
public class SysExpenseController extends BaseController {

    private String PREFIX = "/modular/flowable/sysExpense/";

    @Autowired
    private ISysExpenseService sysExpenseService;

    /**
     * 跳转到报销管理-报销表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "sysExpense.html";
    }

    /**
     * 跳转到添加报销管理-报销表
     */
    @RequestMapping(value = "/sysExpense_add")
    public String sysExpenseAdd() {
        return PREFIX + "sysExpense_add.html";
    }

    /**
     * 跳转到修改报销管理-报销表
     */
//    @RequestMapping(value = "/sysExpense_update", method = RequestMethod.GET)
//    public String sysExpenseUpdate(Long sysExpenseId, Model model) {
//        SysExpense sysExpense = sysExpenseService.selectById(sysExpenseId);
//        model.addAllAttributes(BeanUtil.beanToMap(sysExpense));
//        LogObjectHolder.me().set(sysExpense);
//        return PREFIX + "sysExpense_edit.html";
//    }

    /**
     * 获取报销管理-报销表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.sysExpenseService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 查看当前流程图
     */
    @RequestMapping("/sysExpense_update/{id}")
    public void expenseView(@PathVariable Long id) {
        try {
            sysExpenseService.printProcessImage(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增报销管理-报销表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SysExpense sysExpense) {
        if (ToolUtil.isOneEmpty(sysExpense)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        sysExpense.setCreateUser(ShiroKit.getUserNotNull().getId());
        sysExpense.setCreateTime(new Date());
        sysExpense.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.sysExpenseService.add(sysExpense);
        return SUCCESS_TIP;
    }

    /**
     * 删除报销管理-报销表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam Long sysExpenseId) {
        SysExpense sysExpense = new SysExpense();
        sysExpense.setDeleteFlag(DelFlag.DELETED.getCode());
        sysExpense.setId(sysExpenseId);
        sysExpenseService.updateById(sysExpense);
        return SUCCESS_TIP;
    }

    /**
     * 修改报销管理-报销表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(SysExpense sysExpense) {
        sysExpense.setUpdateUser(ShiroKit.getUserNotNull().getId());
        sysExpense.setUpdateTime(new Date());
        sysExpenseService.updateById(sysExpense);
        return SUCCESS_TIP;
    }

    /**
     * 报销管理-报销表详情
     */
    @RequestMapping(value = "/detail/{sysExpenseId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("sysExpenseId") Integer sysExpenseId) {
        return sysExpenseService.selectById(sysExpenseId);
    }
}
