package com.stylefeng.guns.modular.guar.model;

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
 * 企业担保-相关还款责任人流水表
 * </p>
 *
 * @author wj123
 * @since 2019-06-19
 */
@TableName("GUAR_RESPONSIBLE_INFO_FB")
@Data
public class GuarResponsibleInfoFb implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;
    /**
     * 企业担保账户信息ID
     */
    @TableField("GUAR_ACCT_ID")
    private Double guarAcctId;
    /**
     * 身份类别
     */
    @TableField("INFO_ID_TYPE")
    private String infoIdType;
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
    /**
     * 创建人用户主键ID
     */
    @TableField("CREATE_USER")
    private Long createUser;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 最后修改人员的用户ID
     */
    @TableField("UPDATE_USER")
    private Long updateUser;
    /**
     * 最后修改时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;
    /**
     * 删除标志
     */
    @TableField("DELETE_FLAG")
    private String deleteFlag;


}
