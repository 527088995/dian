package com.stylefeng.guns.generator.action;


import com.stylefeng.guns.generator.action.config.WebGeneratorConfig;
import com.stylefeng.guns.generator.action.model.GenQo;

/**
 * 代码生成器,可以生成实体,dao,service,controller,html,js
 *
 * @author ...
 * @Date 2017/5/21 12:38
 */
public class GunsCodeGenerator {

    public static void main(String[] args) {

        GenQo genQo=new GenQo();
        genQo.setUrl("jdbc:oracle:thin:@192.168.0.118:1521:orcl");//数据库url
        genQo.setUserName("guns");//数据库账号
        genQo.setPassword("guns");//数据库密码
//        genQo.setProjectPath("D:/wj/guns-master/guns/guns-admin");//项目地址
        genQo.setProjectPath("C:/Users/Administrator/Desktop/rito2");//项目地址
        genQo.setProjectPackage("com.stylefeng.guns");//项目的包
        genQo.setCorePackage("com.stylefeng.guns.core");//核心模块的包
        genQo.setTableName("GUAR_MOTGA_PLEDGE_FB");//表名称
        genQo.setIgnoreTabelPrefix("");//忽略的表前缀
        genQo.setParentMenuName("系统管理");//父级菜单名称
        /****************业务逻辑区分模块**************************/
        genQo.setAuthor("wj");//作者
        genQo.setBizName("抵质押合同流水表");//确认业务名称
        genQo.setModuleName("guar");//业务需要区分模块名
        /******************************************/

        /**
         * Mybatis-Plus的代码生成器:
         *      mp的代码生成器可以生成实体,mapper,mapper对应的xml,service
         */
        WebGeneratorConfig webGeneratorConfig = new WebGeneratorConfig(genQo);
        /**
         * Mybatis-Plus的代码生成器:
         *      mp的代码生成器可以生成实体,mapper,mapper对应的xml,service
         */
        webGeneratorConfig.doMpGeneration();
        /**
         * guns的生成器:
         *      guns的代码生成器可以生成controller,html页面,页面对应的js
         */
        webGeneratorConfig.doGunsGeneration();
    }

}