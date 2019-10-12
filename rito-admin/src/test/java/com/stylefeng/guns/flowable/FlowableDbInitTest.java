package com.stylefeng.guns.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.junit.Test;

/**
 * 初始化flowable数据库(执行前需要先在数据库创建这个数据库,下面以数据库名flowable为例)
 *
 * @author ...
 * @date 2017-12-02 20:32
 */
public class FlowableDbInitTest {

//    @Test
//    public void init() {
//        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:mysql://106.13.55.232:3306/guns?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT")
//                .setJdbcUsername("root")
//                .setJdbcPassword("1qaz!QAZ")
//                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//
//        ProcessEngine processEngine = cfg.buildProcessEngine();
//    }

    /**使用代码创建工作流需要的23张表*/
    @Test
    public void createTable() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        //连接数据库的配置
        //配置数据库驱动:对应不同数据库类型的驱动
        processEngineConfiguration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        //配置数据库的JDBC URL
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://106.13.55.232:3306/guns?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT&nullCatalogMeansCurrent=true");
        //配置连接数据库的用户名
        processEngineConfiguration.setJdbcUsername("root");
        //配置连接数据库的密码
        processEngineConfiguration.setJdbcPassword("1qaz!QAZ");
        /**
         public static final String DB_SCHEMA_UPDATE_FALSE = "false";不能自动创建表，需要表存在
         public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";先删除表再创建表
         public static final String DB_SCHEMA_UPDATE_TRUE = "true";如果表不存在，自动创建表
         */
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //工作流的核心对象，ProcessEnginee对象
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println("processEngine:"+processEngine);
    }
}
