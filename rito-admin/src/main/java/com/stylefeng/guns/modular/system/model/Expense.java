package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 报销表
 * </p>
 *
 * @author ...
 * @since 2017-12-05
 */
@TableName("sys_expense")
@Data
public class Expense extends Model<Expense> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Integer id;
    /**
     * 报销金额
     */
	private BigDecimal money;
    /**
     * 描述
     */
	private String desc;
	private Date createtime;
    /**
     * 状态: 1.待提交  2:待审核   3.审核通过
     */
	private Integer state;
    /**
     * 用户id
     */
	private Long userid;
    /**
     * 流程定义id
     */
	private String processId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
