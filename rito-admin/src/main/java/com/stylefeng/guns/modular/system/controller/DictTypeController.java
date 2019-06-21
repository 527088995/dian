package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.page.LayuiPageInfo;
import com.stylefeng.guns.core.response.ResponseData;
import com.stylefeng.guns.modular.system.model.DictType;
import com.stylefeng.guns.modular.system.model.params.DictTypeParam;
import com.stylefeng.guns.modular.system.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 字典类型表控制器
 *
 * @author ...
 * @Date 2019-03-13 13:53:54
 */
@Controller
@RequestMapping("/dictType")
public class DictTypeController extends BaseController {

    private String PREFIX = "/modular/system/dictType";

    @Autowired
    private IDictTypeService dictTypeService;

    /**
     * 跳转到主页面
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/dictType.html";
    }

    /**
     * 新增页面
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/dictType_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/dictType_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DictTypeParam dictTypeParam) {
        this.dictTypeService.add(dictTypeParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(DictTypeParam dictTypeParam) {
        this.dictTypeService.update(dictTypeParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(DictTypeParam dictTypeParam) {
        this.dictTypeService.delete(dictTypeParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(DictTypeParam dictTypeParam) {
        DictType detail = this.dictTypeService.selectById(dictTypeParam.getDictTypeId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author ...
     * @Date 2019-03-13
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(DictTypeParam dictTypeParam) {
        return this.dictTypeService.findPageBySpec(dictTypeParam);
    }

}


