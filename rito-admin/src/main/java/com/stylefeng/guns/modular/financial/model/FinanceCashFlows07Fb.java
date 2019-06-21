package com.stylefeng.guns.modular.financial.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 财务报表-现金流量表信息07流水表
 * </p>
 *
 * @author wj123
 * @since 2019-06-17
 */
@TableName("FINANCE_CASH_FLOWS07_FB")
@Data
public class FinanceCashFlows07Fb implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;
    /**
     * 企业名称
     */
    @TableField("ENT_NAME")
    private String entName;
    /**
     * 企业身份标识类型
     */
    @TableField("ENT_CERT_TYPE")
    private String entCertType;
    /**
     * 企业身份标识号码
     */
    @TableField("ENT_CERT_NUM")
    private String entCertNum;
    /**
     * 信息报告日期
     */
    @TableField("RPT_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rptDate;
    /**
     * 报表年份
     */
    @TableField("SHEET_YEAR")
    private String sheetYear;
    /**
     * 报表类型
     */
    @TableField("SHEET_TYPE")
    private String sheetType;
    /**
     * 报表类型细分
     */
    @TableField("SHEET_TYPE_DIVIDE")
    private String sheetTypeDivide;
    /**
     * 审计事务所名称
     */
    @TableField("AUDIT_FIRM_NAME")
    private String auditFirmName;
    /**
     * 审计人员名称
     */
    @TableField("AUDITOR_NAME")
    private String auditorName;
    /**
     * 审计时间
     */
    @TableField("AUDIT_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;
    /**
     * 客户资料维护机构代码
     */
    @TableField("CIMOC")
    private String cimoc;
    /**
     * 报告时点说明代码
     */
    @TableField("RPT_DATE_CODE")
    private String rptDateCode;
    /**
     * 销售商品和提供劳务收到的现金
     */
    @TableField("CRFSOGOROS")
    private Double crfsogoros;
    /**
     * 收到的税费返还
     */
    @TableField("TAX_REFUNDS")
    private Double taxRefunds;
    /**
     * 收到其他与经营活动有关的现金
     */
    @TableField("OCRRTOA")
    private Double ocrrtoa;
    /**
     * 经营活动现金流入小计
     */
    @TableField("TCIFOA")
    private Double tcifoa;
    /**
     * 购买商品、接受劳务支付的现金
     */
    @TableField("CPFGAS")
    private Double cpfgas;
    /**
     * 支付给职工以及为职工支付的现金
     */
    @TableField("CPTAOBOE")
    private Double cptaoboe;
    /**
     * 支付的各项税费
     */
    @TableField("PAYMENTS_OF_ALL_TYPES_OF_TAXES")
    private Double paymentsOfAllTypesOfTaxes;
    /**
     * 支付其他与经营活动有关的现金
     */
    @TableField("OCPFOA")
    private Double ocpfoa;
    /**
     * 经营活动现金流出小计
     */
    @TableField("TCOFOA")
    private Double tcofoa;
    /**
     * 经营活动产生的现金流量净额
     */
    @TableField("NCFOA")
    private Double ncfoa;
    /**
     * 收回投资所收到的现金
     */
    @TableField("CRFROI")
    private Double crfroi;
    /**
     * 取得投资收益所收到的现金
     */
    @TableField("CASH_RECEIVED_FROM_ONVESTMENTS")
    private Double cashReceivedFromOnvestments;
    /**
     * 处置固定资产无形资产和其他长期资产所收回的现金净额
     */
    @TableField("NCRFDOFAAAOLTA")
    private Double ncrfdofaaaolta;
    /**
     * 处置子公司及其他营业单位收到的现金净额
     */
    @TableField("NCIODOSAOBE")
    private Double nciodosaobe;
    /**
     * 收到其他与投资活动有关的现金
     */
    @TableField("CRRTOIA")
    private Double crrtoia;
    /**
     * 投资活动现金流入小计
     */
    @TableField("TCIFIA")
    private Double tcifia;
    /**
     * 购建固定资产无形资产和其他长期资产所支付的现金
     */
    @TableField("CPTAFAIAAOLTA")
    private Double cptafaiaaolta;
    /**
     * 投资所支付的现金
     */
    @TableField("CASH_PAYMENTS_FOR_INVESTMENTS")
    private Double cashPaymentsForInvestments;
    /**
     * 取得子公司及其他营业单位支付的现金净额
     */
    @TableField("NCOOPOSAOBU")
    private Double ncooposaobu;
    /**
     * 支付其他与投资活动有关的现金
     */
    @TableField("CPRTOIA")
    private Double cprtoia;
    /**
     * 投资活动现金流出小计
     */
    @TableField("SUB_TOTAL_OF_CASH_OUTFLOWS")
    private Double subTotalOfCashOutflows;
    /**
     * 投资活动产生的现金流量净额
     */
    @TableField("NCFFIA")
    private Double ncffia;
    /**
     * 吸收投资收到的现金
     */
    @TableField("CASH_RECEIVED_FROM_INVESTORS")
    private Double cashReceivedFromInvestors;
    /**
     * 取得借款收到的现金
     */
    @TableField("CASH_FROM_BORROWINGS")
    private Double cashFromBorrowings;
    /**
     * 收到其他与筹资活动有关的现金
     */
    @TableField("OCRRTFA")
    private Double ocrrtfa;
    /**
     * 筹资活动现金流入小计
     */
    @TableField("TCIFFA")
    private Double tciffa;
    /**
     * 偿还债务所支付的现金
     */
    @TableField("CASH_REPAYMENTS_FOR_DEBTS")
    private Double cashRepaymentsForDebts;
    /**
     * 分配股利、利润或偿付利息所支付的现金
     */
    @TableField("CPFDODOPAE")
    private Double cpfdodopae;
    /**
     * 支付其他与筹资活动有关的现金
     */
    @TableField("CPRTOFA")
    private Double cprtofa;
    /**
     * 筹资活动现金流出小计
     */
    @TableField("TCOFFA")
    private Double tcoffa;
    /**
     * 筹集活动产生的现金流量净额
     */
    @TableField("NCFFFA")
    private Double ncfffa;
    /**
     * 汇率变动对现金及现金等价物的影响
     */
    @TableField("DODRCOC")
    private Double dodrcoc;
    /**
     * 现金及现金等价物净增加额(五)
     */
    @TableField("NICACE")
    private Double nicace;
    /**
     * 期初现金及现金等价物余额
     */
    @TableField("ICACEB")
    private Double icaceb;
    /**
     * 期末现金及现金等价物余额(六)
     */
    @TableField("TFCACEB")
    private Double tfcaceb;
    /**
     * 净利润
     */
    @TableField("NET_PROFIT")
    private Double netProfit;
    /**
     * 资产减值准备
     */
    @TableField("PROVISION_FOR_ASSET_IMPAIRMENT")
    private Double provisionForAssetImpairment;
    /**
     * 固定资产折旧、油气资产折耗、生产性生物资产折旧
     */
    @TableField("DEPRECIATION_OF_FIXED_ASSETS")
    private Double depreciationOfFixedAssets;
    /**
     * 无形资产摊销
     */
    @TableField("AOIA")
    private Double aoia;
    /**
     * 长期待摊费用摊销
     */
    @TableField("AOLTDE")
    private Double aoltde;
    /**
     * 待摊费用减少
     */
    @TableField("DECREASE_OF_DEFFERED_EXPENSES")
    private Double decreaseOfDefferedExpenses;
    /**
     * 预提费用增加
     */
    @TableField("ADDITION_OF_ACCUED_EXPENSE")
    private Double additionOfAccuedExpense;
    /**
     * 处置固定资产无形资产和其他长期资产的损失
     */
    @TableField("LODOFAIAAOLTA")
    private Double lodofaiaaolta;
    /**
     * 固定资产报废损失
     */
    @TableField("LOSOFA")
    private Double losofa;
    /**
     * 公允价值变动损失
     */
    @TableField("POLFCIFV")
    private Double polfcifv;
    /**
     * 财务费用
     */
    @TableField("FINANCE_EXPENSE")
    private Double financeExpense;
    /**
     * 投资损失
     */
    @TableField("LOSSES_ARSING_FROMINVESTMENT")
    private Double lossesArsingFrominvestment;
    /**
     * 递延所得税资产减少
     */
    @TableField("DEFERRED_INCOME_TAX_ASSETS")
    private Double deferredIncomeTaxAssets;
    /**
     * 递延所得税负债增加
     */
    @TableField("DEFERRED_INCOME_TAX_LIABILITIE")
    private Double deferredIncomeTaxLiabilitie;
    /**
     * 存货的减少
     */
    @TableField("DECREASE_IN_INVENTORIES")
    private Double decreaseInInventories;
    /**
     * 经营性应收项目的减少
     */
    @TableField("DIOR")
    private Double dior;
    /**
     * 经营性应付项目的增加
     */
    @TableField("IIOR")
    private Double iior;
    /**
     * 其他1
     */
    @TableField("OTHERS")
    private Double others;
    /**
     * 经营活动产生的现金流量净额2
     */
    @TableField("NCFFOA")
    private Double ncffoa;
    /**
     * 债务转为资本
     */
    @TableField("DEBTS_TRANSFER_TO_CAPITAL")
    private Double debtsTransferToCapital;
    /**
     * 一年内到期的可转换公司债券
     */
    @TableField("ONE_YEAR_DUE_CONVERTIBLE_BONDS")
    private Double oneYearDueConvertibleBonds;
    /**
     * 融资租入固定资产
     */
    @TableField("FRTTFA")
    private Double frttfa;
    /**
     * 其他2
     */
    @TableField("NON_CASH_OTHERS")
    private Double nonCashOthers;
    /**
     * 现金的期末余额
     */
    @TableField("CASH_AT_THE_END_OF_PERIOD")
    private Double cashAtTheEndOfPeriod;
    /**
     * 现金的期初余额
     */
    @TableField("CATBOTP")
    private Double catbotp;
    /**
     * 现金等价物的期末余额
     */
    @TableField("CEATEOTP")
    private Double ceateotp;
    /**
     * 现金等价物的期初余额
     */
    @TableField("CEATBOTP")
    private Double ceatbotp;
    /**
     * 现金及现金等价物净增加额
     */
    @TableField("NIICACE")
    private Double niicace;
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
     * 创建人用户主键ID
     */
    @TableField("CREATE_USER")
    private Long createUser;
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


}
