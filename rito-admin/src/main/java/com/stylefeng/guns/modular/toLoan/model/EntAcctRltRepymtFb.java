package com.stylefeng.guns.modular.toLoan.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 企业借贷-相关还款人信息流水表
 * </p>
 *
 * @author wj
 * @since 2019-06-11
 */
@TableName("ENT_ACCT_RLT_REPYMT_FB")
@Data
public class EntAcctRltRepymtFb implements Serializable {

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
     * 身份类别
     */
    @TableField("ARLP_ID_TYPE")
    private String arlpIdType;
    /**
     * 责任人名称
     */
    @TableField("ARLP_NAME")
    private String arlpName;
    /**
     * 责任人身份标识类型
     */
    @TableField("ARLP_CERT_TYPE")
    private String arlpCertType;
    /**
     * 责任人身份标识号码
     */
    @TableField("ARLP_CERT_NUM")
    private String arlpCertNum;
    /**
     * 还款责任人类型
     */
    @TableField("ARLP_TYPE")
    private String arlpType;
    /**
     * 还款责任金额
     */
    @TableField("ARLP_AMT")
    private Long arlpAmt;
    /**
     * 联保标志
     */
    @TableField("WARTY_SIGN")
    private String wartySign;
    /**
     * 保证合同编号
     */
    @TableField("MAX_GUAR_MCC")
    private String maxGuarMcc;
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
