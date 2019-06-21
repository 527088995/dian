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
 * 操作日志
 * </p>
 *
 * @author ...
 * @since 2017-07-11
 */
@TableName("sys_operation_log")
@Data
public class OperationLog extends Model<OperationLog> {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "operation_log_id", type = IdType.ID_WORKER)
	private Long operationLogId;

	/**
	 * 日志类型(字典)
	 */
	@TableField("log_type")
	private String logType;

	/**
	 * 日志名称
	 */
	@TableField("log_name")
	private String logName;

	/**
	 * 用户id
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 类名称
	 */
	@TableField("class_name")
	private String className;

	/**
	 * 方法名称
	 */
	@TableField("method")
	private String method;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 是否成功(字典)
	 */
	@TableField("succeed")
	private String succeed;

	/**
	 * 备注
	 */
	@TableField("message")
	private String message;

	@Override
	protected Serializable pkVal() {
		return this.operationLogId;
	}
}
