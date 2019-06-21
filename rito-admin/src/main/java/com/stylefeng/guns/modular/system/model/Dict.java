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
 * 字典表
 * </p>
 *
 * @author ...
 * @since 2017-07-11
 */
@TableName("sys_dict")
@Data
public class Dict extends Model<Dict> {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典id
	 */
	@TableId(value = "dict_id", type = IdType.ID_WORKER)
	private Long dictId;

	/**
	 * 所属字典类型的id
	 */
	@TableField("dict_type_id")
	private Long dictTypeId;

	/**
	 * 字典编码
	 */
	@TableField("code")
	private String code;

	/**
	 * 字典名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 上级代码id
	 */
	@TableField("parent_id")
	private Long parentId;

	/**
	 * 所有上级id
	 */
	@TableField("parent_ids")
	private String parentIds;

	/**
	 * 状态（字典）
	 */
	@TableField("status")
	private String status;

	/**
	 * 排序
	 */
	@TableField("sort")
	private Integer sort;

	/**
	 * 字典的描述
	 */
	@TableField("description")
	private String description;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 更新时间
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
		return this.dictId;
	}
}
