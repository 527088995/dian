package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author ...
 * @since 2017-07-11
 */
@TableName("sys_relation")
@Data
public class Relation extends Model<Relation> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "relation_id", type = IdType.ID_WORKER)
    private Long relationId;

    /**
     * 菜单id
     */
    @TableField("menu_id")
    private Long menuId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    @Override
    protected Serializable pkVal() {
        return this.relationId;
    }
}
