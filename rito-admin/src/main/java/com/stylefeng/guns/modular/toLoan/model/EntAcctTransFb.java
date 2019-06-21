package com.stylefeng.guns.modular.toLoan.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 企业借贷-特定交易信息流水表
 * </p>
 *
 * @author wj123
 * @since 2019-06-12
 */
@TableName("ENT_ACCT_TRANS_FB")
@Data
public class EntAcctTransFb implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;
    /**
     * 借款账户ID
     */
    @TableField("ENT_ACCT_ID")
    private Double entAcctId;
    /**
     * 交易类型
     */
    @TableField("CHAN_TRAN_TYPE")
    private String chanTranType;
    /**
     * 交易日期
     */
    @TableField("TRAN_DATE")
    private Date tranDate;
    /**
     * 交易金额
     */
    @TableField("TRAN_AMT")
    private Long tranAmt;
    /**
     * 到期日期变更月数
     */
    @TableField("DUE_TRAN_MON")
    private Integer dueTranMon;
    /**
     * 交易明细信息
     */
    @TableField("DET_INFO")
    private String detInfo;
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
