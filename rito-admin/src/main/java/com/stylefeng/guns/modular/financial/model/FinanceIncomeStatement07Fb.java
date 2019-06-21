package com.stylefeng.guns.modular.financial.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 利润及利润分配07流水表
 * </p>
 *
 * @author wj123
 * @since 2019-06-17
 */
@TableName("FINANCE_INCOME_STATEMENT07_FB")
@Data
public class FinanceIncomeStatement07Fb implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;
    /**
     * 企业编号
     */
    @TableField("ENT_NAME")
    private String entName;
    /**
     * 信息报告日期
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
    private Date auditTime;
    /**
     * 客户资料维护机构代码
     */
    @TableField("CIMOC")
    private String cimoc;
    /**
     * 报告时点说明代码
     */
    @TableField("PRT_DATE_CODE")
    private String prtDateCode;
    /**
     * 营业收入
     */
    @TableField("REVENUE_OF_SALES")
    private Double revenueOfSales;
    /**
     * 营业成本
     */
    @TableField("COST_OF_SALES")
    private Double costOfSales;
    /**
     * 营业税金及附加
     */
    @TableField("BUSINESS_AND_OTHER_TAXES")
    private Double businessAndOtherTaxes;
    /**
     * 销售费用
     */
    @TableField("SELLING_EXPENSES")
    private Double sellingExpenses;
    /**
     * 管理费用
     */
    @TableField("GENERAL_AND_ADMINISTRATIVE_EXP")
    private Double generalAndAdministrativeExp;
    /**
     * 财务费用
     */
    @TableField("FINANCIAL_EXPENSE")
    private Double financialExpense;
    /**
     * 资产减值损失
     */
    @TableField("IMPAIRMENT_LOSS_OF_ASSETS")
    private Double impairmentLossOfAssets;
    /**
     * 公允价值变动净收益
     */
    @TableField("POLAFCIFV")
    private Double polafcifv;
    /**
     * 投资净收益
     */
    @TableField("INVESTMENT_INCOME")
    private Double investmentIncome;
    /**
     * 对联营企业和合营企业的投资收益
     */
    @TableField("IIFABACE")
    private Double iifabace;
    /**
     * 营业利润
     */
    @TableField("OPERATING_PROFITS")
    private Double operatingProfits;
    /**
     * 营业外收入
     */
    @TableField("NON_OPERATING_INCOME")
    private Double nonOperatingIncome;
    /**
     * 营业外支出
     */
    @TableField("NON_OPERATING_EXPENSES")
    private Double nonOperatingExpenses;
    /**
     * 非流动资产损失
     */
    @TableField("NON_CURRENT_ASSETS")
    private Double nonCurrentAssets;
    /**
     * 利润总额
     */
    @TableField("PROFIT_BEFORE_TAX")
    private Double profitBeforeTax;
    /**
     * 所得税费用
     */
    @TableField("INCOME_TAX_EXPENSE")
    private Double incomeTaxExpense;
    /**
     * 净利润
     */
    @TableField("NET_PROFIT")
    private Double netProfit;
    /**
     * 基本每股收益
     */
    @TableField("BASIC_EARNINGS_PER_SHARE")
    private Double basicEarningsPerShare;
    /**
     * 稀释每股收益
     */
    @TableField("DILUTED_EARNINGS_PER_SHARE")
    private Double dilutedEarningsPerShare;
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
