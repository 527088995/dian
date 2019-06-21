package com.stylefeng.guns.modular.antiMoneyLaundering.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.CreateXMLUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.antiMoneyLaundering.model.AntiMoneyLaundering;
import com.stylefeng.guns.modular.antiMoneyLaundering.service.IAntiMoneyLaunderingService;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import com.stylefeng.guns.modular.system.warpper.NoticeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 反洗钱控制器
 *
 * @author wj
 * @Date 2019-04-19 20:28:06
 */
@Controller
@RequestMapping("/antiMoneyLaundering")
public class AntiMoneyLaunderingController extends BaseController {

    private String PREFIX = "/modular/antiMoneyLaundering/antiMoneyLaundering/";

    @Autowired
    private IAntiMoneyLaunderingService antiMoneyLaunderingService;

    /**
     * 跳转到反洗钱首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "antiMoneyLaundering.html";
    }

    /**
     * 跳转到添加反洗钱
     */
    @RequestMapping(value = "/antiMoneyLaundering_add")
    public String antiMoneyLaunderingAdd() {
        return PREFIX + "antiMoneyLaundering_add.html";
    }

    /**
     * 跳转到修改反洗钱
     */
    @RequestMapping(value = "/antiMoneyLaundering_update", method = RequestMethod.GET)
    public String antiMoneyLaunderingUpdate(String antiId, Model model) {
        AntiMoneyLaundering antiMoneyLaundering = antiMoneyLaunderingService.selectById(antiId);
        model.addAllAttributes(BeanUtil.beanToMap(antiMoneyLaundering));
        LogObjectHolder.me().set(antiMoneyLaundering);
        return PREFIX + "antiMoneyLaundering_edit.html";
    }

    /**
     * 获取反洗钱列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @DataSource(name = "dataSourceBiz")
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.antiMoneyLaunderingService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增反洗钱
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @DataSource(name = "dataSourceBiz")
    public Object add(AntiMoneyLaundering antiMoneyLaundering) {
        if (ToolUtil.isOneEmpty(antiMoneyLaundering)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        antiMoneyLaundering.setCreateUser(ShiroKit.getUserNotNull().getId());
        antiMoneyLaundering.setCreateTime(new Date());
        antiMoneyLaundering.setDelFlag("0");
        this.antiMoneyLaunderingService.insert(antiMoneyLaundering);
        return SUCCESS_TIP;
    }

    /**
     * 删除反洗钱
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @DataSource(name = "dataSourceBiz")
    public Object delete(@RequestParam Long antiId) {
        AntiMoneyLaundering antiMoneyLaundering = new AntiMoneyLaundering();
        antiMoneyLaundering.setDelFlag("1");
        antiMoneyLaundering.setAntiId(antiId);
        antiMoneyLaunderingService.updateById(antiMoneyLaundering);
        return SUCCESS_TIP;
    }

    /**
     * 修改反洗钱
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @DataSource(name = "dataSourceBiz")
    public Object update(AntiMoneyLaundering antiMoneyLaundering) {
        antiMoneyLaundering.setUpdateUser(ShiroKit.getUserNotNull().getId());
        antiMoneyLaundering.setUpdateTime(new Date());
        antiMoneyLaunderingService.updateById(antiMoneyLaundering);
        return SUCCESS_TIP;
    }

    /**
     * 生成XML
     */
    @RequestMapping(value = "/createXML", method = RequestMethod.POST)
    @ResponseBody
    @DataSource(name = "dataSourceBiz")
    public Object createXML(@RequestParam Long antiId) {
        AntiMoneyLaundering antiMoneyLaundering = this.antiMoneyLaunderingService.createXML(antiId);
        boolean flag = CreateXMLUtil.start(antiMoneyLaundering);
        if (flag == true) {
            return SUCCESS_TIP;
        } else {
            return null;
        }
    }

    /**
     * 反洗钱详情
     */
    @RequestMapping(value = "/detail/{antiId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("antiId") Integer antiId) {
        return antiMoneyLaunderingService.selectById(antiId);
    }
}
