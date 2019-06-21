package com.stylefeng.guns.modular.toLoan.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 企业借贷-抵质押物信息流水表
 * </p>
 *
 * @author wj123
 * @since 2019-06-12
 */
@TableName("ENT_ACCT_CCC_FB")
@Data
public class EntAcctCccFb implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 借款账户ID
     */
    @TableField("ENT_ACCT_ID")
    private Long entAcctId;
    /**
     * 抵质押合同标识码
     */
    @TableField("CCC")
    private String ccc;
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableField("CREATE_USER")
    private Long createUser;
    @TableField("UPDATE_TIME")
    private Date updateTime;
    @TableField("UPDATE_USER")
    private Long updateUser;
    @TableField("DELETE_FLAG")
    private String deleteFlag;


}
