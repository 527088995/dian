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
import com.stylefeng.guns.modular.guar.model.GuarResponsibleInfoFb;
import com.stylefeng.guns.modular.guar.service.IGuarResponsibleInfoFbService;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 相关还款责任人流水表控制器
 *
 * @author wj
 * @Date 2019-06-19 10:00:49
 */
@Controller
@RequestMapping("/guarResponsibleInfoFb")
public class GuarResponsibleInfoFbController extends BaseController {

    private String PREFIX = "/modular/guar/guarResponsibleInfoFb/";

    @Autowired
    private IGuarResponsibleInfoFbService guarResponsibleInfoFbService;

    /**
     * 跳转到相关还款责任人流水表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "guarResponsibleInfoFb.html";
    }

    /**
     * 跳转到添加相关还款责任人流水表
     */
    @RequestMapping(value = "/guarResponsibleInfoFb_add")
    public String guarResponsibleInfoFbAdd() {
        return PREFIX + "guarResponsibleInfoFb_add.html";
    }

    /**
     * 跳转到修改相关还款责任人流水表
     */
    @RequestMapping(value = "/guarResponsibleInfoFb_update", method = RequestMethod.GET)
    public String guarResponsibleInfoFbUpdate(Long guarResponsibleInfoFbId, Model model) {
        GuarResponsibleInfoFb guarResponsibleInfoFb = guarResponsibleInfoFbService.selectById(guarResponsibleInfoFbId);
        model.addAllAttributes(BeanUtil.beanToMap(guarResponsibleInfoFb));
        LogObjectHolder.me().set(guarResponsibleInfoFb);
        return PREFIX + "guarResponsibleInfoFb_edit.html";
    }

    /**
     * 获取相关还款责任人流水表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.guarResponsibleInfoFbService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增相关还款责任人流水表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @BussinessLog(value = "新增相关还款责任人流水表", key = "guarResponsibleInfoFb")
    @ResponseBody
    public Object add(GuarResponsibleInfoFb guarResponsibleInfoFb) {
        if (ToolUtil.isOneEmpty(guarResponsibleInfoFb)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        guarResponsibleInfoFb.setCreateUser(ShiroKit.getUserNotNull().getId());
        guarResponsibleInfoFb.setCreateTime(new Date());
        guarResponsibleInfoFb.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.guarResponsibleInfoFbService.insert(guarResponsibleInfoFb);
        return SUCCESS_TIP;
    }

    /**
     * 删除相关还款责任人流水表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @BussinessLog(value = "删除相关还款责任人流水表", key = "guarResponsibleInfoFbId")
    @ResponseBody
    public Object delete(@RequestParam Long guarResponsibleInfoFbId) {
        GuarResponsibleInfoFb guarResponsibleInfoFb = new GuarResponsibleInfoFb();
        guarResponsibleInfoFb.setDeleteFlag(DelFlag.DELETED.getCode());
        guarResponsibleInfoFb.setId(guarResponsibleInfoFbId);
        guarResponsibleInfoFbService.updateById(guarResponsibleInfoFb);
        return SUCCESS_TIP;
    }

    /**
     * 修改相关还款责任人流水表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @BussinessLog(value = "修改相关还款责任人流水表", key = "guarResponsibleInfoFbId")
    @ResponseBody
    public Object update(GuarResponsibleInfoFb guarResponsibleInfoFb) {
        guarResponsibleInfoFb.setUpdateUser(ShiroKit.getUserNotNull().getId());
        guarResponsibleInfoFb.setUpdateTime(new Date());
        guarResponsibleInfoFbService.updateById(guarResponsibleInfoFb);
        return SUCCESS_TIP;
    }

    /**
     * 相关还款责任人流水表详情
     */
    @RequestMapping(value = "/detail/{guarResponsibleInfoFbId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("guarResponsibleInfoFbId") Integer guarResponsibleInfoFbId) {
        return guarResponsibleInfoFbService.selectById(guarResponsibleInfoFbId);
    }
}
