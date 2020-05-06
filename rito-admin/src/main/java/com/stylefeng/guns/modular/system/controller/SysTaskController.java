package com.stylefeng.guns.modular.system.controller;

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
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.model.SysTask;
import com.stylefeng.guns.modular.system.service.IDictService;
import com.stylefeng.guns.modular.system.service.ISysTaskService;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 任务管理控制器
 *
 * @author wj
 * @Date 2019-06-13 10:54:58
 */
@Controller
@RequestMapping("/sysTask")
public class SysTaskController extends BaseController {

    private String PREFIX = "/modular/system/sysTask/";

    @Autowired
    private ISysTaskService sysTaskService;
    @Autowired
    private IDictService dictService;

    /**
     * 跳转到任务管理首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "sysTask.html";
    }

    /**
     * 跳转到添加任务管理
     */
    @RequestMapping(value = "/sysTask_add")
    public String sysTaskAdd(Model model) {
        List<Dict> all = dictService.selectByParentCode("TASK_DISABLED");
        model.addAttribute("dictName", all);
        return PREFIX + "sysTask_add.html";
    }

    /**
     * 跳转到修改任务管理
     */
    @RequestMapping(value = "/sysTask_update", method = RequestMethod.GET)
    public String sysTaskUpdate(Long sysTaskId, Model model) {
        SysTask sysTask = sysTaskService.selectById(sysTaskId);
        model.addAllAttributes(BeanUtil.beanToMap(sysTask));
        List<Dict> all = dictService.selectByParentCode("TASK_DISABLED");
        model.addAttribute("dictName", all);
        LogObjectHolder.me().set(sysTask);
        return PREFIX + "sysTask_edit.html";
    }

    /**
     * 获取任务管理列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.sysTaskService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增任务管理
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SysTask sysTask) {
        if (ToolUtil.isOneEmpty(sysTask.getJobClass(),sysTask.getCron(),sysTask.getName())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        if(!CronExpression.isValidExpression(sysTask.getCron())){
            throw new ServiceException(BizExceptionEnum.CRON_RESULT);
        }
        sysTask.setCreateUser(ShiroKit.getUserNotNull().getId());
        sysTask.setCreateTime(new Date());
        sysTask.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.sysTaskService.insert(sysTask);
        return SUCCESS_TIP;
    }

    /**
     * 删除任务管理
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam Long sysTaskId) {
        sysTaskService.delete(sysTaskId);
        return SUCCESS_TIP;
    }

    /**
     * 修改任务管理
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(SysTask sysTask) {
        sysTask.setUpdateUser(ShiroKit.getUserNotNull().getId());
        sysTask.setUpdateTime(new Date());
        this.sysTaskService.updateById(sysTask);
        return SUCCESS_TIP;
    }

    /**
     * 任务管理详情
     */
    @RequestMapping(value = "/detail/{sysTaskId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("sysTaskId") Integer sysTaskId) {
        return sysTaskService.selectById(sysTaskId);
    }
}
