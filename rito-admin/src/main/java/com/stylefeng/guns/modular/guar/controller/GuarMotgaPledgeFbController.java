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
import com.stylefeng.guns.modular.guar.model.GuarMotgaPledgeFb;
import com.stylefeng.guns.modular.guar.service.IGuarMotgaPledgeFbService;
import com.stylefeng.guns.modular.system.warpper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 抵质押合同流水表控制器
 *
 * @author wj
 * @Date 2019-06-19 10:01:43
 */
@Controller
@RequestMapping("/guarMotgaPledgeFb")
public class GuarMotgaPledgeFbController extends BaseController {

    private String PREFIX = "/modular/guar/guarMotgaPledgeFb/";

    @Autowired
    private IGuarMotgaPledgeFbService guarMotgaPledgeFbService;

    /**
     * 跳转到抵质押合同流水表首页
     */
    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "guarMotgaPledgeFb.html";
    }

    /**
     * 跳转到添加抵质押合同流水表
     */
    @RequestMapping(value = "/guarMotgaPledgeFb_add")
    public String guarMotgaPledgeFbAdd() {
        return PREFIX + "guarMotgaPledgeFb_add.html";
    }

    /**
     * 跳转到修改抵质押合同流水表
     */
    @RequestMapping(value = "/guarMotgaPledgeFb_update", method = RequestMethod.GET)
    public String guarMotgaPledgeFbUpdate(Long guarMotgaPledgeFbId, Model model) {
        GuarMotgaPledgeFb guarMotgaPledgeFb = guarMotgaPledgeFbService.selectById(guarMotgaPledgeFbId);
        model.addAllAttributes(BeanUtil.beanToMap(guarMotgaPledgeFb));
        LogObjectHolder.me().set(guarMotgaPledgeFb);
        return PREFIX + "guarMotgaPledgeFb_edit.html";
    }

    /**
     * 获取抵质押合同流水表列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.guarMotgaPledgeFbService.list(page, condition);
        page.setRecords(new BaseWrapper(list).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 新增抵质押合同流水表
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @BussinessLog(value = "新增抵质押合同流水表", key = "guarMotgaPledgeFb")
    @ResponseBody
    public Object add(GuarMotgaPledgeFb guarMotgaPledgeFb) {
        if (ToolUtil.isOneEmpty(guarMotgaPledgeFb)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        guarMotgaPledgeFb.setCreateUser(ShiroKit.getUserNotNull().getId());
        guarMotgaPledgeFb.setCreateTime(new Date());
        guarMotgaPledgeFb.setDeleteFlag(DelFlag.UNDELETED.getCode());
        this.guarMotgaPledgeFbService.insert(guarMotgaPledgeFb);
        return SUCCESS_TIP;
    }

    /**
     * 删除抵质押合同流水表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @BussinessLog(value = "删除抵质押合同流水表", key = "guarMotgaPledgeFbId")
    @ResponseBody
    public Object delete(@RequestParam Long guarMotgaPledgeFbId) {
        GuarMotgaPledgeFb guarMotgaPledgeFb = new GuarMotgaPledgeFb();
        guarMotgaPledgeFb.setDeleteFlag(DelFlag.DELETED.getCode());
        guarMotgaPledgeFb.setGuarAcctId(guarMotgaPledgeFbId);
        guarMotgaPledgeFbService.updateById(guarMotgaPledgeFb);
        return SUCCESS_TIP;
    }

    /**
     * 修改抵质押合同流水表
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @BussinessLog(value = "修改抵质押合同流水表", key = "guarMotgaPledgeFbId")
    @ResponseBody
    public Object update(GuarMotgaPledgeFb guarMotgaPledgeFb) {
        guarMotgaPledgeFb.setUpdateUser(ShiroKit.getUserNotNull().getId());
        guarMotgaPledgeFb.setUpdateTime(new Date());
        guarMotgaPledgeFbService.updateById(guarMotgaPledgeFb);
        return SUCCESS_TIP;
    }

    /**
     * 抵质押合同流水表详情
     */
    @RequestMapping(value = "/detail/{guarMotgaPledgeFbId}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("guarMotgaPledgeFbId") Integer guarMotgaPledgeFbId) {
        return guarMotgaPledgeFbService.selectById(guarMotgaPledgeFbId);
    }
}
