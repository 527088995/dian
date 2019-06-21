package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 定时任务日志
 * </p>
 *
 * @author wj123
 * @since 2019-06-13
 */
@TableName("SYS_TASK_LOG")
@Data
public class SysTaskLog implements Serializable {

    public static final int EXE_FAILURE_RESULT = 0;
    public static final int EXE_SUCCESS_RESULT = 1;

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
     * 执行时间
     */
    @TableField("EXEC_AT")
    private Date execAt;
    /**
     * 执行结果（成功:1、失败:0)
     */
    @TableField("EXEC_SUCCESS")
    private int execSuccess;
    /**
     * 抛出异常
     */
    @TableField("JOB_EXCEPTION")
    private String jobException;
    @TableField("ID_TASK")
    private Long idTask;
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableField("CREATE_USER")
    private Long createUser;
    @TableField("UPDATE_TIME")
    private Date updateTime;
    @TableField("UPDATE_USER")
    private Long updateUser;



}
