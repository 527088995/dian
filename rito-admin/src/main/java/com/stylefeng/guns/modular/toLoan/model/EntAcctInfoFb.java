package com.stylefeng.guns.modular.toLoan.model;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * <p>
 * 企业借贷账户信息流水表
 * </p>
 *
 * @author wj
 * @since 2019-06-11
 */
@TableName("ENT_ACCT_INFO_FB")
@Data
public class EntAcctInfoFb extends Model<EntAcctInfoFb> {

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
     * 借款人名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 借款人身份标识类型
     */
    @TableField("ID_TYPE")
    private String idType;
    /**
     * 借款人身份标识号码
     */
    @TableField("ID_NUM")
    private String idNum;
    /**
     * 业务管理机构代码
     */
    @TableField("MNGMT_ORG_CODE")
    private String mngmtOrgCode;
    /**
     * 借款业务大类
     */
    @TableField("BUSI_LINES")
    private String busiLines;
    /**
     * 借款业务种类细分
     */
    @TableField("BUSI_DTL_LINES")
    private String busiDtlLines;
    /**
     * 开户日期
     */
    @TableField("OPEN_DATE")
    private Date openDate;
    /**
     * 币种
     */
    @TableField("CY")
    private String cy;
    /**
     * 信用额度
     */
    @TableField("ACCT_CRED_LINE")
    private Long acctCredLine;
    /**
     * 借款金额
     */
    @TableField("LOAN_AMT")
    private Long loanAmt;
    /**
     * 分次放款标志
     */
    @TableField("FLAG")
    private String flag;
    /**
     * 到期日期
     */
    @TableField("DUE_DATE")
    private Date dueDate;
    /**
     * 还款方式
     */
    @TableField("REPAY_MODE")
    private String repayMode;
    /**
     * 还款频率
     */
    @TableField("REPAY_FREQCY")
    private String repayFreqcy;
    /**
     * 业务申请地行政区划代码
     */
    @TableField("APPLY_BUSI_DIST")
    private String applyBusiDist;
    /**
     * 担保方式
     */
    @TableField("GUAR_MODE")
    private String guarMode;
    /**
     * 其他还款保证方式
     */
    @TableField("OTH_REPY_GUAR_WAY")
    private String othRepyGuarWay;
    /**
     * 借款期限分类
     */
    @TableField("LOAN_TIME_LIM_CAT")
    private String loanTimeLimCat;
    /**
     * 贷款发放形式
     */
    @TableField("LOAFRM")
    private String loafrm;
    /**
     * 贷款实际投向
     */
    @TableField("ACT_INVEST")
    private String actInvest;
    /**
     * 业务经营类型
     */
    @TableField("FUND_SOU")
    private String fundSou;
    /**
     * 资产转让标志
     */
    @TableField("ASSET_TRAND_FLAG")
    private String assetTrandFlag;
    /**
     * 责任人个数
     */
    @TableField("RLT_REPYMT_NM")
    private Integer rltRepymtNm;
    /**
     * 抵质押合同个数
     */
    @TableField("CC_NUM")
    private Integer ccNum;
    /**
     * 授信协议标识码
     */
    @TableField("MCC")
    private String mcc;
    /**
     * 初始债券人名称
     */
    @TableField("INIT_CRED_NAME")
    private String initCredName;
    /**
     * 初始债权人机构代码
     */
    @TableField("INIT_CED_ORG_CODE")
    private String initCedOrgCode;
    /**
     * 原债务种类
     */
    @TableField("ORIG_DBT_CATE")
    private String origDbtCate;
    /**
     * 债券转移时还款状态
     */
    @TableField("INIT_RPY_STS")
    private String initRpySts;
    /**
     * 账户状态
     */
    @TableField("ACCT_STATUS")
    private String acctStatus;
    /**
     * 余额
     */
    @TableField("ACCT_BAL")
    private Long acctBal;
    /**
     * 余额变化日期
     */
    @TableField("BAL_CHG_DATE")
    private Date balChgDate;
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
     * 剩余还款月数
     */
    @TableField("PYMT_PRD")
    private Integer pymtPrd;
    /**
     * 当前逾期总额
     */
    @TableField("TOT_OVERD")
    private Long totOverd;
    /**
     * 当前逾期本金
     */
    @TableField("OVERD_PRINC")
    private Long overdPrinc;
    /**
     * 当前逾期天数
     */
    @TableField("OVERD_DY")
    private Integer overdDy;
    /**
     * 最近一次实际还款日期
     */
    @TableField("LAT_RPY_DATE")
    private Date latRpyDate;
    /**
     * 最近一次实际还款金额
     */
    @TableField("LAT_RPY_AMT")
    private Long latRpyAmt;
    /**
     * 最近一次实际归还本金
     */
    @TableField("LAT_RPY_PRINC_AMT")
    private Long latRpyPrincAmt;
    /**
     * 还款形式
     */
    @TableField("RPMT_TYPE")
    private String rpmtType;
    /**
     * 最近一次约定还款日
     */
    @TableField("LAT_AGRR_RPY_DATE")
    private Date latAgrrRpyDate;
    /**
     * 最近一次约定还款金额
     */
    @TableField("LAG_AGRR_RPY_AMT")
    private Long lagAgrrRpyAmt;
    /**
     * 下一次约定还款日期
     */
    @TableField("NXT_AGRR_RPY_DATE")
    private Date nxtAgrrRpyDate;
    /**
     * 账户关闭日期
     */
    @TableField("CLOSE_DATE")
    private Date closeDate;
    /**
     * 交易个数
     */
    @TableField("CAG_OF_TRD_NM")
    private Integer cagOfTrdNm;
    /**
     * 信息记录状态 
     */
    @TableField("STATUS")
    private String status;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 删除标志
     */
    @TableField("DELETE_FLAG")
    private String deleteFlag;
    /**
     * 创建人
     */
    @TableField("CREATE_USER")
    private Long createUser;
    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;
    /**
     * 更新人
     */
    @TableField("UPDATE_USER")
    private Long updateUser;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
