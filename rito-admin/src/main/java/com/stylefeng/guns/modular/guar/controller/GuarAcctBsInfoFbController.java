package com.stylefeng.guns.modular.guar.controller;

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
import com.stylefeng.guns.modular.guar.model.GuarAcctBsInfoFb;
import com.stylefeng.guns.modular.guar.service.IGuarAcctBsInfoFbService;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 账户信息流水表控制器
 *
 * @author wj
 * @Date 2019-06-19 10:01:19
 */
@Controller
@RequestMapping("/guarAcctBsInfoFb")
public class GuarAcctBsInfoFbController extends BaseController {

    private String PREFIX = "/modular/guar/guarAcctBsInfoFb/";

    @Autowired
    private IGuarAcctBsInfoFbService guarAcctBsInfoFbService;

    /**
     * 跳转到账户信息流水表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "guarAcctBsInfoFb.html";
    }

    /**
     * 跳转到添加账户信息流水表
     */
    @RequestMapping(value = "/guarAcctBsInfoFb_add")
    public String guarAcctBsInfoFbAdd() {
        return PREFIX + "guarAcctBsInfoFb_add.html";
    }

    /**
     * 跳转到修改账户信息流水表
     */
    @RequestMapping(value = "/guarAcctBsInfoFb_update", method = RequestMethod.GET)
    public String guarAcctBsInfoFbUpdate(Long guarAcctBsInfoFbId, Model model) {
        GuarAcctBsInfoFb guarAcctBsInfoFb = guarAcctBsInfoFbService.selectById(guarAcctBsInfoFbId);
        model.addAllAttributes(BeanUtil.beanToMap(guarAcctBsInfoFb));
        LogObjectHolder.me().set(guarAcctBsInfoFb);
        return PREFIX + "guarAcctBsInfoFb_edit.html";
    }

    /**
     * 获取账户信息流水表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.guarAcctBsInfoFbService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增账户信息流水表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @BussinessLog(value = "新增账户信息流水表", key = "guarAcctBsInfoFb")
    @ResponseBody
    public Object add(GuarAcctBsInfoFb guarAcctBsInfoFb) {
        if (ToolUtil.isOneEmpty(guarAcctBsInfoFb)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        guarAcctBsInfoFb.setCreateUser(ShiroKit.getUserNotNull().getId());
        guarAcctBsInfoFb.setCreateTime(new Date());
        guarAcctBsInfoFb.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.guarAcctBsInfoFbService.insert(guarAcctBsInfoFb);
        return SUCCESS_TIP;
    }

    /**
     * 删除账户信息流水表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @BussinessLog(value = "删除账户信息流水表", key = "guarAcctBsInfoFbId")
    @ResponseBody
    public Object delete(@RequestParam Long guarAcctBsInfoFbId) {
        GuarAcctBsInfoFb guarAcctBsInfoFb = new GuarAcctBsInfoFb();
        guarAcctBsInfoFb.setDeleteFlag(DelFlag.DELETED.getCode());
        guarAcctBsInfoFb.setId(guarAcctBsInfoFbId);
        guarAcctBsInfoFbService.updateById(guarAcctBsInfoFb);
        return SUCCESS_TIP;
    }

    /**
     * 修改账户信息流水表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @BussinessLog(value = "修改账户信息流水表", key = "guarAcctBsInfoFbId")
    @ResponseBody
    public Object update(GuarAcctBsInfoFb guarAcctBsInfoFb) {
        guarAcctBsInfoFb.setUpdateUser(ShiroKit.getUserNotNull().getId());
        guarAcctBsInfoFb.setUpdateTime(new Date());
        guarAcctBsInfoFbService.updateById(guarAcctBsInfoFb);
        return SUCCESS_TIP;
    }

    /**
     * 账户信息流水表详情
     */
    @RequestMapping(value = "/detail/{guarAcctBsInfoFbId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("guarAcctBsInfoFbId") Integer guarAcctBsInfoFbId) {
        return guarAcctBsInfoFbService.selectById(guarAcctBsInfoFbId);
    }
}
