package com.stylefeng.guns.modular.financial.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.constant.state.DelFlag;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.financial.model.FinanceIncomeStatement07Fb;
import com.stylefeng.guns.modular.financial.service.IFinanceIncomeStatement07FbService;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 利润及利润分配07流水表控制器
 *
 * @author wj
 * @Date 2019-06-17 14:44:09
 */
@Controller
@RequestMapping("/financeIncomeStatement07Fb")
public class FinanceIncomeStatement07FbController extends BaseController {

    private String PREFIX = "/modular/financial/financeIncomeStatement07Fb/";

    @Autowired
    private IFinanceIncomeStatement07FbService financeIncomeStatement07FbService;

    /**
     * 跳转到利润及利润分配07流水表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "financeIncomeStatement07Fb.html";
    }

    /**
     * 跳转到添加利润及利润分配07流水表
     */
    @RequestMapping(value = "/financeIncomeStatement07Fb_add")
    public String financeIncomeStatement07FbAdd() {
        return PREFIX + "financeIncomeStatement07Fb_add.html";
    }

    /**
     * 跳转到修改利润及利润分配07流水表
     */
    @RequestMapping(value = "/financeIncomeStatement07Fb_update", method = RequestMethod.GET)
    @BussinessLog(value = "修改利润及利润分配07流水表", key = "financeIncomeStatement07FbId")
    public String financeIncomeStatement07FbUpdate(Long financeIncomeStatement07FbId, Model model) {
        FinanceIncomeStatement07Fb financeIncomeStatement07Fb = financeIncomeStatement07FbService.selectById(financeIncomeStatement07FbId);
        model.addAllAttributes(BeanUtil.beanToMap(financeIncomeStatement07Fb));
        LogObjectHolder.me().set(financeIncomeStatement07Fb);
        return PREFIX + "financeIncomeStatement07Fb_edit.html";
    }

    /**
     * 获取利润及利润分配07流水表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.financeIncomeStatement07FbService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增利润及利润分配07流水表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @BussinessLog(value = "新增利润及利润分配07流水表", key = "financeIncomeStatement07Fb")
    @ResponseBody
    public Object add(FinanceIncomeStatement07Fb financeIncomeStatement07Fb) {
    if (ToolUtil.isOneEmpty(financeIncomeStatement07Fb)) {
                throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
            }
        financeIncomeStatement07Fb.setCreateUser(ShiroKit.getUserNotNull().getId());
        financeIncomeStatement07Fb.setCreateTime(new Date());
        financeIncomeStatement07Fb.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.financeIncomeStatement07FbService.insert(financeIncomeStatement07Fb);
    return SUCCESS_TIP;
    }

    /**
     * 删除利润及利润分配07流水表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @BussinessLog(value = "删除利润及利润分配07流水表", key = "financeIncomeStatement07FbId")
    @ResponseBody
    public Object delete(@RequestParam Long financeIncomeStatement07FbId) {
    FinanceIncomeStatement07Fb financeIncomeStatement07Fb = new FinanceIncomeStatement07Fb();
    financeIncomeStatement07Fb.setDeleteFlag(DelFlag.DELETED.getCode());
    financeIncomeStatement07Fb.setId(financeIncomeStatement07FbId);
        financeIncomeStatement07FbService.updateById(financeIncomeStatement07Fb);
        return SUCCESS_TIP;
    }

    /**
     * 修改利润及利润分配07流水表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(FinanceIncomeStatement07Fb financeIncomeStatement07Fb) {
            financeIncomeStatement07Fb.setUpdateUser(ShiroKit.getUserNotNull().getId());
            financeIncomeStatement07Fb.setUpdateTime(new Date());
        financeIncomeStatement07FbService.updateById(financeIncomeStatement07Fb);
        return SUCCESS_TIP;
    }

    /**
     * 利润及利润分配07流水表详情
     */
    @RequestMapping(value = "/detail/{financeIncomeStatement07FbId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("financeIncomeStatement07FbId") Integer financeIncomeStatement07FbId) {
        return financeIncomeStatement07FbService.selectById(financeIncomeStatement07FbId);
    }
}
