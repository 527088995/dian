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
import com.stylefeng.guns.modular.financial.model.FinanceBalanceSheet07Fb;
import com.stylefeng.guns.modular.financial.service.IFinanceBalanceSheet07FbService;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 财务报表-资产负债07流水表控制器
 *
 * @author wj
 * @Date 2019-06-17 14:45:13
 */
@Controller
@RequestMapping("/financeBalanceSheet07Fb")
public class FinanceBalanceSheet07FbController extends BaseController {

    private String PREFIX = "/modular/financial/financeBalanceSheet07Fb/";

    @Autowired
    private IFinanceBalanceSheet07FbService financeBalanceSheet07FbService;

    /**
     * 跳转到财务报表-资产负债07流水表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "financeBalanceSheet07Fb.html";
    }

    /**
     * 跳转到添加财务报表-资产负债07流水表
     */
    @RequestMapping(value = "/financeBalanceSheet07Fb_add")
    public String financeBalanceSheet07FbAdd() {
        return PREFIX + "financeBalanceSheet07Fb_add.html";
    }

    /**
     * 跳转到修改财务报表-资产负债07流水表
     */
    @RequestMapping(value = "/financeBalanceSheet07Fb_update", method = RequestMethod.GET)
    @BussinessLog(value = "修改财务报表-资产负债07流水表", key = "financeBalanceSheet07FbId")
    public String financeBalanceSheet07FbUpdate(Long financeBalanceSheet07FbId, Model model) {
        FinanceBalanceSheet07Fb financeBalanceSheet07Fb = financeBalanceSheet07FbService.selectById(financeBalanceSheet07FbId);
        model.addAllAttributes(BeanUtil.beanToMap(financeBalanceSheet07Fb));
        LogObjectHolder.me().set(financeBalanceSheet07Fb);
        return PREFIX + "financeBalanceSheet07Fb_edit.html";
    }

    /**
     * 获取财务报表-资产负债07流水表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.financeBalanceSheet07FbService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增财务报表-资产负债07流水表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @BussinessLog(value = "新增财务报表-资产负债07流水表", key = "financeBalanceSheet07Fb")
    @ResponseBody
    public Object add(FinanceBalanceSheet07Fb financeBalanceSheet07Fb) {
        if (ToolUtil.isOneEmpty(financeBalanceSheet07Fb)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        financeBalanceSheet07Fb.setCreateUser(ShiroKit.getUserNotNull().getId());
        financeBalanceSheet07Fb.setCreateTime(new Date());
        financeBalanceSheet07Fb.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.financeBalanceSheet07FbService.insert(financeBalanceSheet07Fb);
        return SUCCESS_TIP;
    }

    /**
     * 删除财务报表-资产负债07流水表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @BussinessLog(value = "删除财务报表-资产负债07流水表", key = "financeBalanceSheet07FbId")
    @ResponseBody
    public Object delete(@RequestParam Long financeBalanceSheet07FbId) {
        FinanceBalanceSheet07Fb financeBalanceSheet07Fb = new FinanceBalanceSheet07Fb();
        financeBalanceSheet07Fb.setDeleteFlag(DelFlag.DELETED.getCode());
        financeBalanceSheet07Fb.setId(financeBalanceSheet07FbId);
        financeBalanceSheet07FbService.updateById(financeBalanceSheet07Fb);
        return SUCCESS_TIP;
    }

    /**
     * 修改财务报表-资产负债07流水表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @BussinessLog(value = "修改财务报表-资产负债07流水表", key = "financeBalanceSheet07FbId")
    @ResponseBody
    public Object update(FinanceBalanceSheet07Fb financeBalanceSheet07Fb) {
        financeBalanceSheet07Fb.setUpdateUser(ShiroKit.getUserNotNull().getId());
        financeBalanceSheet07Fb.setUpdateTime(new Date());
        financeBalanceSheet07FbService.updateById(financeBalanceSheet07Fb);
        return SUCCESS_TIP;
    }

    /**
     * 财务报表-资产负债07流水表详情
     */
    @RequestMapping(value = "/detail/{financeBalanceSheet07FbId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("financeBalanceSheet07FbId") Integer financeBalanceSheet07FbId) {
        return financeBalanceSheet07FbService.selectById(financeBalanceSheet07FbId);
    }
}
