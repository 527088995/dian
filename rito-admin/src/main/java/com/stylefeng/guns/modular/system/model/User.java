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
 * 管理员表
 * </p>
 *
 * @author ...
 * @since 2017-07-11
 */
@TableName("sys_user")
@Data
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "user_id", type = IdType.ID_WORKER)
	private Long userId;

	/**
	 * 头像
	 */
	@TableField("avatar")
	private String avatar;

	/**
	 * 账号
	 */
	@TableField("account")
	private String account;

	/**
	 * 密码
	 */
	@TableField("password")
	private String password;

	/**
	 * md5密码盐
	 */
	@TableField("salt")
	private String salt;

	/**
	 * 名字
	 */
	@TableField("name")
	private String name;

	/**
	 * 生日
	 */
	@TableField("birthday")
	private Date birthday;

	/**
	 * 性别(字典)
	 */
	@TableField("sex")
	private String sex;

	/**
	 * 电子邮件
	 */
	@TableField("email")
	private String email;

	/**
	 * 电话
	 */
	@TableField("phone")
	private String phone;

	/**
	 * 角色id(多个逗号隔开)
	 */
	@TableField("role_id")
	private String roleId;

	/**
	 * 部门id(多个逗号隔开)
	 */
	@TableField("dept_id")
	private Long deptId;

	/**
	 * 状态(字典)
	 */
	@TableField("status")
	private String status;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 创建人
	 */
	@TableField(value = "create_user", fill = FieldFill.INSERT)
	private Long createUser;

	/**
	 * 更新时间
	 */
	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	private Date updateTime;

	/**
	 * 更新人
	 */
	@TableField(value = "update_user", fill = FieldFill.UPDATE)
	private Long updateUser;

	/**
	 * 乐观锁
	 */
	@TableField("version")
	private Integer version;

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}
}
