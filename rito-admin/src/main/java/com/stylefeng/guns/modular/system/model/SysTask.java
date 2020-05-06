package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wj123
 * @since 2019-06-13
 */
@TableName("SYS_TASK")
@Data
public class SysTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;
    /**
     * 任务名
     */
    @TableField("NAME")
    private String name;
    /**
     * 任务组
     */
    @TableField("JOB_GROUP")
    private String jobGroup;
    /**
     * 执行类
     */
    @TableField("JOB_CLASS")
    private String jobClass;
    /**
     * 任务说明
     */
    @TableField("NOTE")
    private String note;
    /**
     * 定时规则
     */
    @TableField("CRON")
    private String cron;
    /**
     * 执行参数
     */
    @TableField("DATA")
    private String data;
    /**
     * 执行时间
     */
    @TableField("EXEC_AT")
    private Date execAt;
    /**
     * 执行结果
     */
    @TableField("EXEC_RESULT")
    private String execResult;
    /**
     * 是否禁用
     */
    @TableField("DISABLED")
    private String disabled;
    /**
     * 是否允许并发
     */
    @TableField("CONCURRENT")
    private boolean concurrent;
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableField("CREATE_USER")
    private Long createUser;
    @TableField("UPDATE_TIME")
    private Date updateTime;
    @TableField("UPDATE_USER")
    private Long updateUser;
    @TableField("DELETE_FLAG")
    private String deleteFlag;

}
