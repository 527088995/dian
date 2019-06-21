package com.stylefeng.guns.modular.system.model;

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
 * 角色表
 * </p>
 *
 * @author ...
 * @since 2017-07-11
 */
@TableName("sys_role")
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @TableId(value = "role_id", type = IdType.ID_WORKER)
    private Long roleId;

    /**
     * 父角色id
     */
    @TableField("pid")
    private Long pid;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 提示
     */
    @TableField("description")
    private String description;

    /**
     * 序号
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Integer version;

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
     * 创建用户
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改用户
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Long updateUser;

}
