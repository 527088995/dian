package com.stylefeng.guns.modular.demos.controller;

import cn.afterturn.easypoi.entity.vo.MapExcelConstants;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.page.LayuiPageInfo;
import com.stylefeng.guns.core.response.ResponseData;
import com.stylefeng.guns.modular.demos.entity.ExcelItem;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel导入导出示例
 *
 * @author ...
 * @Date 2019/3/9 11:03
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private IUserService userService;

    @Autowired
    private GunsProperties gunsProperties;

    /**
     * excel导入页面
     *
     * @author ...
     * @Date 2019/3/9 11:03
     */
    @RequestMapping("/import")
    public String importIndex() {
        return "/modular/demos/excel_import.html";
    }

    /**
     * 上传excel填报
     */
    @RequestMapping("/uploadExcel")
    @ResponseBody
    public ResponseData uploadExcel(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String name = file.getOriginalFilename();
        request.getSession().setAttribute("upFile", name);
        String fileSavePath = gunsProperties.getFileUploadPath();
        try {
            file.transferTo(new File(fileSavePath + name));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", IdWorker.getId());
        return ResponseData.success(0, "上传成功", map);
    }

    /**
     * 获取上传成功的数据
     */
    @RequestMapping("/getUploadData")
    @ResponseBody
    public Object getUploadData(HttpServletRequest request) {
        String name = (String) request.getSession().getAttribute("upFile");
        String fileSavePath = gunsProperties.getFileUploadPath();
        if (name != null) {
            File file = new File(fileSavePath + name);
            try {
                ImportParams params = new ImportParams();
                params.setTitleRows(1);
                params.setHeadRows(1);
                List result = ExcelImportUtil.importExcel(file, ExcelItem.class, params);

                LayuiPageInfo returns = new LayuiPageInfo();
                returns.setCount(result.size());
                returns.setData(result);
                return returns;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * excel导出
     *
     * @author ...
     * @Date 2019/3/9 11:03
     */
    @RequestMapping("/export")
    public void export(ModelMap modelMap, HttpServletRequest request,
                       HttpServletResponse response) {

        //初始化表头
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("用户id", "user_id"));
        entity.add(new ExcelExportEntity("头像", "avatar"));
        entity.add(new ExcelExportEntity("账号", "account"));
        entity.add(new ExcelExportEntity("姓名", "name"));
        entity.add(new ExcelExportEntity("生日", "birthday"));
        entity.add(new ExcelExportEntity("性别", "sex"));
        entity.add(new ExcelExportEntity("邮箱", "email"));
        entity.add(new ExcelExportEntity("电话", "phone"));
        entity.add(new ExcelExportEntity("角色id", "role_id"));
        entity.add(new ExcelExportEntity("部门id", "dept_id"));
        entity.add(new ExcelExportEntity("状态", "status"));
        entity.add(new ExcelExportEntity("创建时间", "create_time"));

        //初始化化数据
        Wrapper<User> wrapper = new EntityWrapper<>();
        List<Map<String, Object>> maps = userService.selectMaps(wrapper);
        ArrayList<Map<String, Object>> total = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            total.addAll(maps);
        }

        ExportParams params = new ExportParams("Guns管理系统所有用户", "用户表", ExcelType.XSSF);
        Object put = modelMap.put(MapExcelConstants.MAP_LIST, total);
        modelMap.put(MapExcelConstants.ENTITY_LIST, entity);
        modelMap.put(MapExcelConstants.PARAMS, params);
        modelMap.put(MapExcelConstants.FILE_NAME, "Guns管理系统所有用户");
        PoiBaseView.render(modelMap, request, response, MapExcelConstants.EASYPOI_MAP_EXCEL_VIEW);
    }
}
