package com.stylefeng.guns.modular.generator.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 数据库表
 * </p>
 *
 * @author wj
 * @since 2019-04-25
 */
@TableName("sys_database_info")
@Getter
@Setter
@ToString
public class DatabaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "DB_ID", type = IdType.ID_WORKER)
    private Long dbId;

    /**
     * 标题
     */
    @TableField("DB_NAME")
    private String dbName;

    /**
     * 标题
     */
    @TableField("JDBC_DRIVER")
    private String jdbcDriver;

    /**
     * 标题
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 标题
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 标题
     */
    @TableField("JDBC_URL")
    private String jdbcUrl;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("CREATE_USER")
    private Long createUser;

    /**
     * 修改时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField("UPDATE_USER")
    private Long updateUser;


}
