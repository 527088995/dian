package com.stylefeng.guns.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.HttpContext;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.SysTaskLog;
import com.stylefeng.guns.modular.system.service.ISysTaskLogService;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 任务管理日志控制器
 *
 * @author wj
 * @Date 2019-06-13 14:27:11
 */
@Controller
@RequestMapping("/sysTaskLog")
public class SysTaskLogController extends BaseController {

    private String PREFIX = "/modular/system/sysTaskLog/";

    @Autowired
    private ISysTaskLogService sysTaskLogService;

    /**
     * 跳转到任务管理日志首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "sysTaskLog.html";
    }

    /**
     * 跳转到添加任务管理日志
     */
    @RequestMapping(value = "/sysTaskLog_add")
    public String sysTaskLogAdd() {
        return PREFIX + "sysTaskLog_add.html";
    }

    /**
     * 跳转到修改任务管理日志
     */
    @RequestMapping(value = "/sysTaskLog_update", method = RequestMethod.GET)
    public String sysTaskLogUpdate(Long sysTaskLogId, Model model) {
        SysTaskLog sysTaskLog = sysTaskLogService.selectById(sysTaskLogId);
        model.addAllAttributes(BeanUtil.beanToMap(sysTaskLog));
        LogObjectHolder.me().set(sysTaskLog);
        return PREFIX + "sysTaskLog_edit.html";
    }

    @RequestMapping(value = "/viewLog/{id}")
    public String viewLog(@PathVariable("id") Long id, Model model) {
        model.addAttribute("taskId", id);
        return PREFIX + "sysTaskLog.html";
    }

    /**
     * 获取任务管理日志列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list() {
        HttpServletRequest request = HttpContext.getRequest();
        String taskId = request.getParameter("taskId");
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.sysTaskLogService.list(page, taskId);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增任务管理日志
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SysTaskLog sysTaskLog) {
        if (ToolUtil.isOneEmpty(sysTaskLog)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        sysTaskLog.setCreateUser(ShiroKit.getUserNotNull().getId());
        sysTaskLog.setCreateTime(new Date());
        this.sysTaskLogService.insert(sysTaskLog);
        return SUCCESS_TIP;
    }

    /**
     * 删除任务管理日志
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam Long sysTaskLogId) {
        SysTaskLog sysTaskLog = new SysTaskLog();
        sysTaskLog.setId(sysTaskLogId);
        sysTaskLogService.updateById(sysTaskLog);
        return SUCCESS_TIP;
    }

    /**
     * 修改任务管理日志
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(SysTaskLog sysTaskLog) {
        sysTaskLog.setUpdateUser(ShiroKit.getUserNotNull().getId());
        sysTaskLog.setUpdateTime(new Date());
        sysTaskLogService.updateById(sysTaskLog);
        return SUCCESS_TIP;
    }

    /**
     * 任务管理日志详情
     */
    @RequestMapping(value = "/detail/{sysTaskLogId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("sysTaskLogId") Integer sysTaskLogId) {
        return sysTaskLogService.selectById(sysTaskLogId);
    }
}
