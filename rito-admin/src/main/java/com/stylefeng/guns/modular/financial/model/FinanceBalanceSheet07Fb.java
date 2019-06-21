package com.stylefeng.guns.modular.financial.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 财务报表-资产负债07流水表
 * </p>
 *
 * @author wj123
 * @since 2019-06-17
 */
@TableName("FINANCE_BALANCE_SHEET07_FB")
@Data
public class FinanceBalanceSheet07Fb implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * ID
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
     * 货币资金
     */
    @TableField("CURRENCY_FUNDS")
    private Double currencyFunds;
    /**
     * 交易性金融资产
     */
    @TableField("FINANCIAL_ASSETS_HELD_FOR_TRAD")
    private Double financialAssetsHeldForTrad;
    /**
     * 应收票据
     */
    @TableField("NOTES_RECEIVABLE")
    private Double notesReceivable;
    /**
     * 应收账款
     */
    @TableField("ACCOUNTS_RECEIVABLE")
    private Double accountsReceivable;
    /**
     * 预付账款
     */
    @TableField("PREPAYMENTS")
    private Double prepayments;
    /**
     * 应收利息
     */
    @TableField("INTEREST_RECEIVABLE")
    private Double interestReceivable;
    /**
     * 应收股利
     */
    @TableField("DIVIDENDS_RECEIVABLE")
    private Double dividendsReceivable;
    /**
     * 其他应收款
     */
    @TableField("OTHER_RECEIVABLES")
    private Double otherReceivables;
    /**
     * 存货
     */
    @TableField("INVENTORIES")
    private Double inventories;
    /**
     * 一年内到期的非流动资产
     */
    @TableField("CPONCA")
    private Double cponca;
    /**
     * 其他流动资产
     */
    @TableField("OTHER_CURRENT_ASSETS")
    private Double otherCurrentAssets;
    /**
     * 流动资产合计
     */
    @TableField("TOTAL_CURRENT_ASSETS")
    private Double totalCurrentAssets;
    /**
     * 可供出售的金融资产
     */
    @TableField("FAAFS")
    private Double faafs;
    /**
     * 持有至到期投资
     */
    @TableField("HELD_TO_MATURITY_INVESTMENTS")
    private Double heldToMaturityInvestments;
    /**
     * 长期股权投资
     */
    @TableField("LONG_TERM_EQUITYINVESTMENT")
    private Double longTermEquityinvestment;
    /**
     * 长期应收款
     */
    @TableField("LONG_TERM_RECEIVABLES")
    private Double longTermReceivables;
    /**
     * 投资性房地产
     */
    @TableField("INVESTMENT_PROPERTIES")
    private Double investmentProperties;
    /**
     * 固定资产
     */
    @TableField("FIXED_ASSETS")
    private Double fixedAssets;
    /**
     * 在建工程
     */
    @TableField("CONSTRUCTION_IN_PROGRESS")
    private Double constructionInProgress;
    /**
     * 工程物资
     */
    @TableField("CONSTRUCTION_MATERIALS")
    private Double constructionMaterials;
    /**
     * 固定资产清理
     */
    @TableField("FAPFD")
    private Double fapfd;
    /**
     * 生产性生物资产
     */
    @TableField("NON_CURRENT_BIOLOGICAL_ASSETS")
    private Double nonCurrentBiologicalAssets;
    /**
     * 油气资产
     */
    @TableField("OIL_AND_GAS_ASSETS")
    private Double oilAndGasAssets;
    /**
     * 无形资产
     */
    @TableField("INTANGIBLE_ASSETS")
    private Double intangibleAssets;
    /**
     * 开发支出
     */
    @TableField("DEVELOPMENT_DISBURSEMENTS")
    private Double developmentDisbursements;
    /**
     * 商誉
     */
    @TableField("GOODWILL")
    private Double goodwill;
    /**
     * 长期待摊费用
     */
    @TableField("LONG_TERM_DEFERRED_EXPENSES")
    private Double longTermDeferredExpenses;
    /**
     * 递延所得税资产
     */
    @TableField("DEFERRED_TAX_ASSETS")
    private Double deferredTaxAssets;
    /**
     * 其他非流动资产
     */
    @TableField("OTHER_NON_CURRENT_ASSETS")
    private Double otherNonCurrentAssets;
    /**
     * 非流动资产总计
     */
    @TableField("TOTAL_NON_CURRENT_ASSETS")
    private Double totalNonCurrentAssets;
    /**
     * 资产总计
     */
    @TableField("TOTAL_ASSETS")
    private Double totalAssets;
    /**
     * 短期借款
     */
    @TableField("SHORT_TERM_BORROWINGS")
    private Double shortTermBorrowings;
    /**
     * 交易性金融负债
     */
    @TableField("FLHFT")
    private Double flhft;
    /**
     * 应付票据
     */
    @TableField("NOTES_PAYABLE")
    private Double notesPayable;
    /**
     * 应付账款
     */
    @TableField("ACCOUNTS_PAYABLE")
    private Double accountsPayable;
    /**
     * 预收账款
     */
    @TableField("RECEIPTS_INADVANCE")
    private Double receiptsInadvance;
    /**
     * 应付利息
     */
    @TableField("INTEREST_PAYABLE")
    private Double interestPayable;
    /**
     * 应付职工薪酬
     */
    @TableField("EMPLOYEE_BENEFITS_PAYABLE")
    private Double employeeBenefitsPayable;
    /**
     * 应交税费
     */
    @TableField("TAXS_PAYABLE")
    private Double taxsPayable;
    /**
     * 应付股利
     */
    @TableField("DIVIDENDS_PAYABLE")
    private Double dividendsPayable;
    /**
     * 其他应付款
     */
    @TableField("OTHER_PAYABLES")
    private Double otherPayables;
    /**
     * 一年内到期的非流动负债
     */
    @TableField("CPOLTL")
    private Double cpoltl;
    /**
     * 其他流动负债
     */
    @TableField("OTHER_CURRENT_LIABILITIES")
    private Double otherCurrentLiabilities;
    /**
     * 流动负债合计
     */
    @TableField("TOTAL_CURRENT_LIABILITIES")
    private Double totalCurrentLiabilities;
    /**
     * 长期借款
     */
    @TableField("LONG_TERM_BORROWINGS")
    private Double longTermBorrowings;
    /**
     * 应付债券
     */
    @TableField("BONDS_PAYABLES")
    private Double bondsPayables;
    /**
     * 长期应付款
     */
    @TableField("LONG_TERM_PAYABLES")
    private Double longTermPayables;
    /**
     * 专项应付款
     */
    @TableField("GRANTS_PAYABLE")
    private Double grantsPayable;
    /**
     * 预计负债
     */
    @TableField("PROVISIONS")
    private Double provisions;
    /**
     * 递延所得税负债
     */
    @TableField("DEFERRED_TAXLIABILITIES")
    private Double deferredTaxliabilities;
    /**
     * 其他非流动负债
     */
    @TableField("OTHER_NON_CURRENT_LIABILITIES")
    private Double otherNonCurrentLiabilities;
    /**
     * 非流动负债合计
     */
    @TableField("TOTAL_NON_CURRENT_LIABILITIES")
    private Double totalNonCurrentLiabilities;
    /**
     * 负债合计
     */
    @TableField("TOTAL_LIABILITIES")
    private Double totalLiabilities;
    /**
     * 实收资本（或股本）
     */
    @TableField("PCOSC")
    private Double pcosc;
    /**
     * 资本公积
     */
    @TableField("CAPITALR_RSERVE")
    private Double capitalrRserve;
    /**
     * 减：库存股
     */
    @TableField("LESS_TREASURY_STOCKS")
    private Double lessTreasuryStocks;
    /**
     * 盈余公积
     */
    @TableField("SURPLUS_RESERVE")
    private Double surplusReserve;
    /**
     * 未分配利润
     */
    @TableField("UNAPPROPRIATED_PROFIT")
    private Double unappropriatedProfit;
    /**
     * 所有者权益合计
     */
    @TableField("TOTAL_EQUITY")
    private Double totalEquity;
    /**
     * 负债和所有者权益合计
     */
    @TableField("TOTAL_EQUITY_AND_LIABILITIES")
    private Double totalEquityAndLiabilities;
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
