package com.stylefeng.guns.core.util;

import com.stylefeng.guns.modular.generator.po.DatabaseInfo;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作工具类
 *
 * @author ...
 * @Date 2019/1/13 18:34
 */
@Slf4j
public class DbUtil {

    public static List<Map<String, Object>> selectTables(DatabaseInfo dbInfo) {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        if (dbInfo.getJdbcDriver().contains("mysql")) {
            //mysql代码生成
            try {
                Class.forName(dbInfo.getJdbcDriver());
                Connection conn = DriverManager.getConnection(dbInfo.getJdbcUrl(), dbInfo.getUserName(), dbInfo.getPassword());

                String jdbcUrl = dbInfo.getJdbcUrl();
                int first = jdbcUrl.lastIndexOf("/") + 1;
                int last = jdbcUrl.indexOf("?");
                String dbName = jdbcUrl.substring(first, last);
                PreparedStatement preparedStatement = conn.prepareStatement("select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + dbName + "'");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    HashMap<String, Object> map = new HashMap<>();
                    String tableName = resultSet.getString("tableName");
                    String tableComment = resultSet.getString("tableComment");
                    map.put("tableName", tableName);
                    map.put("tableComment", tableComment);
                    list.add(map);
                }
                return list;
            } catch (Exception ex) {
                log.error("执行sql出现问题！", ex);
                return null;
            }
        } else {
            //oracle代码生成
            try {
                Class.forName(dbInfo.getJdbcDriver());
                Connection conn = DriverManager.getConnection(dbInfo.getJdbcUrl(), dbInfo.getUserName(), dbInfo.getPassword());

                String jdbcUrl = dbInfo.getJdbcUrl();
                int first = jdbcUrl.lastIndexOf("/") + 1;
                int last = jdbcUrl.indexOf("?");
                // String dbName = jdbcUrl.substring(first, last);
                //PreparedStatement preparedStatement = conn.prepareStatement("select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + dbName + "'");
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM ALL_TAB_COMMENTS WHERE OWNER='GUNS'");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    HashMap<String, Object> map = new HashMap<>();
                    String tableName = resultSet.getString("TABLE_NAME");
                    String tableComment = resultSet.getString("Comments");
                    map.put("tableName", tableName);
                    map.put("tableComment", tableComment);
                    list.add(map);
                }
                return list;
            } catch (Exception ex) {
                log.error("执行sql出现问题！", ex);
                return null;
            }
        }
    }

}
