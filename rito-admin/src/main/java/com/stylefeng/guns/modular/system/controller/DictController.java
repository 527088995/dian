package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.exception.RequestEmptyException;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.page.LayuiPageInfo;
import com.stylefeng.guns.core.response.ResponseData;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.model.DictType;
import com.stylefeng.guns.modular.system.model.params.DictParam;
import com.stylefeng.guns.modular.system.model.result.DictResult;
import com.stylefeng.guns.modular.system.service.IDictService;
import com.stylefeng.guns.modular.system.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 基础字典控制器
 *
 * @author ...
 * @Date 2019-03-13 13:53:53
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    private String PREFIX = "/modular/system/dict";

    @Autowired
    private IDictService dictService;

    @Autowired
    private IDictTypeService dictTypeService;

    /**
     * 跳转到主页面
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("")
    public String index(@RequestParam("dictTypeId") Long dictTypeId, Model model) {
        model.addAttribute("dictTypeId", dictTypeId);

        //获取type的名称
        DictType dictType = dictTypeService.selectById(dictTypeId);
        if (dictType == null) {
            throw new RequestEmptyException();
        }
        model.addAttribute("dictTypeName", dictType.getName());

        return PREFIX + "/dict.html";
    }

    /**
     * 新增页面
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("/add")
    public String add(@RequestParam("dictTypeId") Long dictTypeId, Model model) {
        model.addAttribute("dictTypeId", dictTypeId);

        //获取type的名称
        DictType dictType = dictTypeService.selectById(dictTypeId);
        if (dictType == null) {
            throw new RequestEmptyException();
        }

        model.addAttribute("dictTypeName", dictType.getName());
        return PREFIX + "/dict_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam("dictId") Long dictId, Model model) {

        //获取type的id
        Dict dict = dictService.selectById(dictId);
        if (dict == null) {
            throw new RequestEmptyException();
        }

        //获取type的名称
        DictType dictType = dictTypeService.selectById(dict.getDictTypeId());
        if (dictType == null) {
            throw new RequestEmptyException();
        }

        model.addAttribute("dictTypeId", dict.getDictTypeId());
        model.addAttribute("dictTypeName", dictType.getName());

        return PREFIX + "/dict_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ...
     * @Date 2019-03-13
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DictParam dictParam) {
        this.dictService.add(dictParam);
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
    public ResponseData editItem(DictParam dictParam) {
        this.dictService.update(dictParam);
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
    public ResponseData delete(DictParam dictParam) {
        this.dictService.deleteById(dictParam.getDictId());
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
    public ResponseData detail(DictParam dictParam) {
        DictResult dictResult = this.dictService.dictDetail(dictParam.getDictId());
        return ResponseData.success(dictResult);
    }

    /**
     * 查询列表
     *
     * @author ...
     * @Date 2019-03-13
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(DictParam dictParam) {
        return this.dictService.findPageBySpec(dictParam);
    }

    /**
     * 获取某个类型下字典树的列表，ztree格式
     *
     * @author ...
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping(value = "/ztree")
    @ResponseBody
    public List<ZTreeNode> ztree(@RequestParam("dictTypeId") Long dictTypeId, @RequestParam(value = "dictId", required = false) Long dictId) {
        return this.dictService.dictTreeList(dictTypeId, dictId);
    }

}


