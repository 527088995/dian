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
 * 字典类型表
 * </p>
 *
 * @author ...
 * @since 2019-04-01
 */
@TableName("sys_dict_type")
@Data
public class DictType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典类型id
     */
    @TableId(value = "dict_type_id", type = IdType.ID_WORKER)
    private Long dictTypeId;

    /**
     * 字典类型编码
     */
    @TableField("code")
    private String code;

    /**
     * 字典类型名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典描述
     */
    @TableField("description")
    private String description;

    /**
     * 是否是系统字典，Y-是，N-否
     */
    @TableField("system_flag")
    private String systemFlag;

    /**
     * 状态(字典)
     */
    @TableField("status")
    private String status;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 添加时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Long updateUser;

}
