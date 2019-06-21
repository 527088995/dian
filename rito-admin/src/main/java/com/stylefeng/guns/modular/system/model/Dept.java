package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author ...
 * @since 2017-07-11
 */
@TableName("sys_dept")
@Data
public class Dept extends Model<Dept> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "dept_id", type = IdType.ID_WORKER)
    private Long deptId;

    /**
     * 父部门id
     */
    @TableField("pid")
    private Long pid;

    /**
     * 父级ids
     */
    @TableField("pids")
    private String pids;

    /**
     * 简称
     */
    @TableField("simple_name")
    private String simpleName;

    /**
     * 全称
     */
    @TableField("full_name")
    private String fullName;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 版本（乐观锁保留字段）
     */
    @TableField("version")
    private Integer version;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Long updateUser;


    @Override
    protected Serializable pkVal() {
        return this.deptId;
    }
}
