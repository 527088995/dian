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
import com.stylefeng.guns.modular.financial.model.FinanceCashFlows07Fb;
import com.stylefeng.guns.modular.financial.service.IFinanceCashFlows07FbService;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 财务报表-现金流量表信息07流水表控制器
 *
 * @author wj
 * @Date 2019-06-17 14:44:49
 */
@Controller
@RequestMapping("/financeCashFlows07Fb")
public class FinanceCashFlows07FbController extends BaseController {

    private String PREFIX = "/modular/financial/financeCashFlows07Fb/";

    @Autowired
    private IFinanceCashFlows07FbService financeCashFlows07FbService;

    /**
     * 跳转到财务报表-现金流量表信息07流水表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "financeCashFlows07Fb.html";
    }

    /**
     * 跳转到添加财务报表-现金流量表信息07流水表
     */
    @RequestMapping(value = "/financeCashFlows07Fb_add")
    public String financeCashFlows07FbAdd() {
        return PREFIX + "financeCashFlows07Fb_add.html";
    }

    /**
     * 跳转到修改财务报表-现金流量表信息07流水表
     */
    @RequestMapping(value = "/financeCashFlows07Fb_update", method = RequestMethod.GET)
    @BussinessLog(value = "修改财务报表-现金流量表信息07流水表", key = "financeCashFlows07FbId")
    public String financeCashFlows07FbUpdate(Long financeCashFlows07FbId, Model model) {
        FinanceCashFlows07Fb financeCashFlows07Fb = financeCashFlows07FbService.selectById(financeCashFlows07FbId);
        model.addAllAttributes(BeanUtil.beanToMap(financeCashFlows07Fb));
        LogObjectHolder.me().set(financeCashFlows07Fb);
        return PREFIX + "financeCashFlows07Fb_edit.html";
    }

    /**
     * 获取财务报表-现金流量表信息07流水表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.financeCashFlows07FbService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增财务报表-现金流量表信息07流水表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @BussinessLog(value = "新增财务报表-现金流量表信息07流水表", key = "financeCashFlows07Fb")
    @ResponseBody
    public Object add(FinanceCashFlows07Fb financeCashFlows07Fb) {
        if (ToolUtil.isOneEmpty(financeCashFlows07Fb)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        financeCashFlows07Fb.setCreateUser(ShiroKit.getUserNotNull().getId());
        financeCashFlows07Fb.setCreateTime(new Date());
        financeCashFlows07Fb.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.financeCashFlows07FbService.insert(financeCashFlows07Fb);
        return SUCCESS_TIP;
    }

    /**
     * 删除财务报表-现金流量表信息07流水表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @BussinessLog(value = "删除财务报表-现金流量表信息07流水表", key = "financeCashFlows07FbId")
    @ResponseBody
    public Object delete(@RequestParam Long financeCashFlows07FbId) {
        FinanceCashFlows07Fb financeCashFlows07Fb = new FinanceCashFlows07Fb();
        financeCashFlows07Fb.setDeleteFlag(DelFlag.DELETED.getCode());
        financeCashFlows07Fb.setId(financeCashFlows07FbId);
        financeCashFlows07FbService.updateById(financeCashFlows07Fb);
        return SUCCESS_TIP;
    }

    /**
     * 修改财务报表-现金流量表信息07流水表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(FinanceCashFlows07Fb financeCashFlows07Fb) {
        financeCashFlows07Fb.setUpdateUser(ShiroKit.getUserNotNull().getId());
        financeCashFlows07Fb.setUpdateTime(new Date());
        financeCashFlows07FbService.updateById(financeCashFlows07Fb);
        return SUCCESS_TIP;
    }

    /**
     * 财务报表-现金流量表信息07流水表详情
     */
    @RequestMapping(value = "/detail/{financeCashFlows07FbId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("financeCashFlows07FbId") Integer financeCashFlows07FbId) {
        return financeCashFlows07FbService.selectById(financeCashFlows07FbId);
    }
}
