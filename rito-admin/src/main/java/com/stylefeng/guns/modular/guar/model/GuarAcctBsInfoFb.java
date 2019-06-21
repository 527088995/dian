package com.stylefeng.guns.modular.guar.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 企业担保-账户信息流水表
 * </p>
 *
 * @author wj123
 * @since 2019-06-19
 */
@TableName("GUAR_ACCT_BS_INFO_FB")
@Data
public class GuarAcctBsInfoFb implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;
    /**
     * 账户类型
     */
    @TableField("ACCT_TYPE")
    private String acctType;
    /**
     * 账户标识码
     */
    @TableField("ACCT_CODE")
    private String acctCode;
    /**
     * 信息报告日期
     */
    @TableField("RPT_DATE")
    private Date rptDate;
    /**
     * 报告时点说明代码
     */
    @TableField("RPT_DATE_CODE")
    private String rptDateCode;
    /**
     * 债务人名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 债务人身份标识类型
     */
    @TableField("ID_TYPE")
    private String idType;
    /**
     * 债务人身份标识号码
     */
    @TableField("ID_NUM")
    private String idNum;
    /**
     * 业务管理机构代码
     */
    @TableField("MNGMT_ORG_CODE")
    private String mngmtOrgCode;
    /**
     * 担保业务大类
     */
    @TableField("BUSI_LINES")
    private String busiLines;
    /**
     * 担保业务种类细分
     */
    @TableField("BUSI_DTIL_LINES")
    private String busiDtilLines;
    /**
     * 开户日期
     */
    @TableField("OPEN_DATE")
    private Date openDate;
    /**
     * 担保金额
     */
    @TableField("GUAR_AMT")
    private Long guarAmt;
    /**
     * 币种
     */
    @TableField("CY")
    private String cy;
    /**
     * 到期日期
     */
    @TableField("DUE_DATE")
    private Date dueDate;
    /**
     * 反担保方式
     */
    @TableField("GUAR_MODE")
    private String guarMode;
    /**
     * 其他还款保证方式
     */
    @TableField("OTH_REP_GUAR_WAY")
    private String othRepGuarWay;
    /**
     * 保证金百分比
     */
    @TableField("SEC_DEP")
    private Integer secDep;
    /**
     * 担保合同文本编号
     */
    @TableField("CTRCT_TXT_CODE")
    private String ctrctTxtCode;
    /**
     * 责任人个数
     */
    @TableField("RLT_REPYMT_NM")
    private Integer rltRepymtNm;
    /**
     * 抵质押合同个数
     */
    @TableField("CC_NM")
    private Integer ccNm;
    /**
     * 授信协议标识码
     */
    @TableField("MCC")
    private String mcc;
    /**
     * 账户状态
     */
    @TableField("ACCT_STATUS")
    private String acctStatus;
    /**
     * 在保余额
     */
    @TableField("LOAN_AMT")
    private Long loanAmt;
    /**
     * 余额变化日期
     */
    @TableField("REPAY_PRD")
    private Date repayPrd;
    /**
     * 五级分类
     */
    @TableField("FIVE_CATE")
    private String fiveCate;
    /**
     * 五级分类认定日期
     */
    @TableField("FIVE_CATE_ADJ_DATE")
    private Date fiveCateAdjDate;
    /**
     * 风险敞口
     */
    @TableField("RIEX")
    private Long riex;
    /**
     * 代偿（垫款）标志
     */
    @TableField("COMP_ADV_FLAG")
    private String compAdvFlag;
    /**
     * 账户关闭日期
     */
    @TableField("CLOSE_DATE")
    private Date closeDate;
    /**
     * 信息记录状态 
     */
    @TableField("STATUS")
    private String status;
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
