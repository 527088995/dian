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
 * 登录记录
 * </p>
 *
 * @author ...
 * @since 2017-07-11
 */
@TableName("sys_login_log")
@Data
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "login_log_id", type = IdType.ID_WORKER)
	private Long loginLogId;

	/**
	 * 日志名称
	 */
	@TableField("log_name")
	private String logName;

	/**
	 * 管理员id
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 是否执行成功
	 */
	@TableField("succeed")
	private String succeed;

	/**
	 * 具体消息
	 */
	@TableField("message")
	private String message;

	/**
	 * 登录ip
	 */
	@TableField("ip_address")
	private String ipAddress;


	@Override
	protected Serializable pkVal() {
		return this.loginLogId;
	}
}
