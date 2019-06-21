package com.stylefeng.guns.modular.generator.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.response.SuccessResponseData;
import com.stylefeng.guns.core.util.DbUtil;
import com.stylefeng.guns.generator.action.config.WebGeneratorConfig;
import com.stylefeng.guns.generator.action.model.GenQo;
import com.stylefeng.guns.modular.generator.po.ContextParam;
import com.stylefeng.guns.modular.generator.po.DatabaseInfo;
import com.stylefeng.guns.modular.generator.po.MpParam;
import com.stylefeng.guns.modular.generator.service.IDatabaseInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 代码生成控制器
 *
 * @author ...
 * @date 2019-01-30-2:39 PM
 */
@Controller
@RequestMapping("/generator")
public class GeneratorController {

    private String PREFIX = "/modular/generator/";


    @Autowired
    private IDatabaseInfoService databaseInfoService;

    /**
     * 数据库管理主页
     *
     * @author ...
     * @Date 2019/1/30 2:49 PM
     */
    @RequestMapping("/db")
    public String index() {
        return PREFIX + "/db.html";
    }

    /**
     * 新增页面
     *
     * @author ...
     * @Date 2019-01-11
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/db_add.html";
    }

    /**
     * 新增
     *
     * @author ...
     * @Date 2019-01-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Object addItem(DatabaseInfo databaseInfo) {
        this.databaseInfoService.insert(databaseInfo);
        return new SuccessResponseData();
    }

    /**
     * 删除
     *
     * @author ...
     * @Date 2019-01-11
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long dbId) {
        this.databaseInfoService.deleteById(dbId);
        return new SuccessResponseData();
    }

    /**
     * 获取数据源列表
     *
     * @author ...
     * @Date 2019/1/30 2:49 PM
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list() {
        Wrapper<DatabaseInfo> wrapper = new EntityWrapper<>();
        List<DatabaseInfo> all = databaseInfoService.selectList(wrapper);

        Page<DatabaseInfo> objectPage = new Page<>();
        objectPage.setRecords(all);

        return LayuiPageFactory.createPageInfo(objectPage);
    }

    /**
     * 获取某个数据源下的所有表
     *
     * @author ...
     * @Date 2019/1/30 2:49 PM
     */
    @RequestMapping("/tableList")
    @ResponseBody
    public Object tableList(Long dbId) {
        try {
            DatabaseInfo databaseInfo = databaseInfoService.selectById(dbId);
            List<Map<String, Object>> maps = DbUtil.selectTables(databaseInfo);
            Page<Map<String, Object>> objectPage = new Page<>();
            objectPage.setRecords(maps);
            return LayuiPageFactory.createPageInfo(objectPage);
        } catch (Exception e) {
            Page<Map<String, Object>> objectPage = new Page<>();
            return LayuiPageFactory.createPageInfo(objectPage);
        }
    }


    /**
     * 代码生成主页
     *
     * @author ...
     * @Date 2019/1/30 2:49 PM
     */
    @RequestMapping("")
    public String index(Model model) {
        Wrapper<DatabaseInfo> wrapper = new EntityWrapper<>();
        List<DatabaseInfo> all = databaseInfoService.selectList(wrapper);
        model.addAttribute("dataSources", all);

        return PREFIX + "/gen.html";
    }

    /**
     * 执行代码生成
     *
     * @author ...
     * @Date 2019-01-11
     */
    @RequestMapping(value = "/execute")
    @ResponseBody
    public ResponseEntity<InputStreamResource> execute(String author, String proPackage, String removePrefix,
                                                       Long dataSourceId, String tables) {
        tables = tables.substring(3);

        String[] tableArray = tables.split("CAT");


        DatabaseInfo databaseInfo = databaseInfoService.selectById(dataSourceId);

        ContextParam contextParam = new ContextParam();
        contextParam.setAuthor(author);
        contextParam.setProPackage(proPackage);
        contextParam.setJdbcDriver(databaseInfo.getJdbcDriver());
        contextParam.setJdbcUserName(databaseInfo.getUserName());
        contextParam.setJdbcPassword(databaseInfo.getPassword());
        contextParam.setJdbcUrl(databaseInfo.getJdbcUrl());

        //获取临时目录
        long fileName = IdWorker.getId();
        String tempPath = System.getProperty("java.io.tmpdir") + File.separator + "gunsGeneration" + File.separator + fileName;
        contextParam.setOutputPath(tempPath);

        MpParam mpContextParam = new MpParam();
        mpContextParam.setGeneratorInterface(true);
        mpContextParam.setIncludeTables(tableArray);

        if (StrUtil.isNotEmpty(removePrefix)) {
            mpContextParam.setRemoveTablePrefix(new String[]{removePrefix});
        }

        //代码生成contextParam
        //GunsExecutor.executor(contextParam, mpContextParam);

        //打包下载代码
        File zip = ZipUtil.zip(tempPath);

        return renderFile(fileName + ".zip", zip.getAbsolutePath());
    }

    /**
     * 生成代码
     */
    @ApiOperation("生成代码")
    @RequestMapping(value = "/generate")
    @ResponseBody
    public ResponseEntity<InputStreamResource> generate(String author, String proPackage, String removePrefix,
                                                        Long dataSourceId, String tables, String bizName,String moduleName) {
        tables = tables.substring(3);

        //获取临时目录
        long fileName = IdWorker.getId();
        String tempPath = System.getProperty("java.io.tmpdir") + File.separator + "gunsGeneration" + File.separator + fileName;
        //获取数据库链接
        DatabaseInfo databaseInfo = databaseInfoService.selectById(dataSourceId);
        GenQo genQo = new GenQo();
        genQo.setUrl(databaseInfo.getJdbcUrl());
        genQo.setUserName(databaseInfo.getUserName());
        genQo.setPassword(databaseInfo.getPassword());
        genQo.setProjectPackage(proPackage);//项目的包
        genQo.setCorePackage("com.stylefeng.guns.core");//核心模块的包
        genQo.setProjectPath(tempPath);//项目地址
        genQo.setTableName(tables);//表名称
        genQo.setIgnoreTabelPrefix(removePrefix);//忽略的表前缀
        genQo.setParentMenuName("系统管理");//父级菜单名称

        /****************业务逻辑区分模块**************************/
        genQo.setAuthor(author);//作者
        genQo.setBizName(bizName);//确认业务名称
        genQo.setModuleName(moduleName);//业务需要区分模块名

        WebGeneratorConfig webGeneratorConfig = new WebGeneratorConfig(genQo);
        webGeneratorConfig.doMpGeneration();
        webGeneratorConfig.doGunsGeneration();

        //打包下载代码
        File zip = ZipUtil.zip(tempPath);

        return renderFile(fileName + ".zip", zip.getAbsolutePath());
    }

    /**
     * 返回前台文件流
     *
     * @param fileName    文件名
     * @param inputStream 输入流
     * @return
     * @author 0x0001
     */
    private ResponseEntity<InputStreamResource> renderFile(String fileName, InputStream inputStream) {
        InputStreamResource resource = new InputStreamResource(inputStream);
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<>(resource, headers, HttpStatus.CREATED);
    }

    /**
     * 返回前台文件流
     *
     * @author ...
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<InputStreamResource> renderFile(String fileName, String filePath) {
        try {
            final FileInputStream inputStream = new FileInputStream(filePath);
            return renderFile(fileName, inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件读取错误");
        }
    }

    /**
     * 返回前台文件流
     *
     * @author ...
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<InputStreamResource> renderFile(String fileName, byte[] fileBytes) {
        return renderFile(fileName, new ByteArrayInputStream(fileBytes));
    }


}
