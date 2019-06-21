package com.stylefeng.guns.modular.flowable.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 测试工作流
 * </p>
 *
 * @author wj123
 * @since 2019-06-12
 */
@TableName("SYS_EXPENSE")
@Data
public class SysExpense implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;
    /**
     * 报销金额
     */
    @TableField("MONEY")
    private Long money;
    /**
     * 描述
     */
    @TableField("DESCC")
    private String descc;
    /**
     * 流程Id
     */
    @TableField("PROCESS_ID")
    private String processId;
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableField("CREATE_USER")
    private Long createUser;
    @TableField("UPDATE_TIME")
    private Date updateTime;
    @TableField("UPDATE_USER")
    private Long updateUser;
    /**
     * 删除标记
     */
    @TableField("DELETE_FLAG")
    private String deleteFlag;
    /**
     * 审核状态 1.待提交  2:待审核   3.审核通过 4:驳回
     */
    @TableField("STATE")
    private Integer state;


}
