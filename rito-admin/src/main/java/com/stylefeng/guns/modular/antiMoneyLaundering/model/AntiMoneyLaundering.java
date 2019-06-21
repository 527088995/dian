package com.stylefeng.guns.modular.antiMoneyLaundering.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 反洗钱
 * </p>
 *
 * @author wj
 * @since 2019-04-19
 */
@TableName("sys_anti_money_laundering")
@Data
public class AntiMoneyLaundering implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ANTI_ID", type = IdType.ID_WORKER)
    private Long antiId;

    /**
     * 报告机构编码
     */
    @TableField("RICD")
    private String ricd;

    /**
     * 上报网点代码
     */
    @TableField("RPNC")
    private String rpnc;

    /**
     * 行业类别
     */
    @TableField("RITP")
    private String ritp;

    /**
     * 可疑交易报告紧急程度
     */
    @TableField("DETR")
    private String detr;

    /**
     * 报送次数标志
     */
    @TableField("TORP")
    private String torp;

    /**
     * 初次报送的可疑交易报告报文名称
     */
    @TableField("ORXN")
    private String orxn;

    /**
     * 报送方向
     */
    @TableField("DORP")
    private String dorp;

    /**
     * 其他报送方向
     */
    @TableField("ODRP")
    private String odrp;

    /**
     * 可疑交易报告触发点
     */
    @TableField("TPTR")
    private String tptr;

    /**
     * 其他可疑交易报告触发点
     */
    @TableField("OTPR")
    private String otpr;

    /**
     * 资金交易及客户行为情况
     */
    @TableField("STCB")
    private String stcb;

    /**
     * 疑点分析
     */
    @TableField("AOSP")
    private String aosp;

    /**
     * 疑似涉罪类型
     */
    @TableField("TOSC")
    private String tosc;

    /**
     * 可疑交易特征代码
     */
    @TableField("STCR")
    private String stcr;

    /**
     * 可疑交易或事件起始日期
     */
    @TableField("SBDT")
    private Date sbdt;

    /**
     * 可疑交易或事件结束日期
     */
    @TableField("SEDT")
    private Date sedt;

    /**
     * 交易信息备注
     */
    @TableField("ROTF")
    private String rotf;

    /**
     * 可疑主体职业（对私）或行业（对公）
     */
    @TableField("SEVC")
    private String sevc;

    /**
     * 可疑主体姓名/名称
     */
    @TableField("SENM")
    private String senm;

    /**
     * 可疑主体身份证件/证明文件类型
     */
    @TableField("SETP")
    private String setp;

    /**
     * 其他身份证件/证明文件类型
     */
    @TableField("OITP")
    private String oitp;

    /**
     * 可疑主体身份证件/证明文件号码
     */
    @TableField("SEID")
    private String seid;

    /**
     * 可疑主体国籍
     */
    @TableField("STNT")
    private String stnt;

    /**
     * 可疑主体联系方式
     */
    @TableField("SCIF")
    private String scif;

    /**
     * 可疑主体法定代表人姓名
     */
    @TableField("SRNM")
    private String srnm;

    /**
     * 可疑主体法定代表人身份证件类型
     */
    @TableField("SRIT")
    private String srit;

    /**
     * 可疑主体法定代表人其他身份证件/证明文件类型
     */
    @TableField("ORIT")
    private String orit;

    /**
     * 可疑主体法定代表人身份证件号码
     */
    @TableField("SRID")
    private String srid;

    /**
     * 可疑主体控股股东或实际控制人名称
     */
    @TableField("SCNM")
    private String scnm;

    /**
     * 可疑主体控股股东或实际控制人身份证件/证明文件类型
     */
    @TableField("SCIT")
    private String scit;

    /**
     * 可疑主体控股股东其他身份证件/证明文件类型
     */
    @TableField("OCIT")
    private String ocit;

    /**
     * 可疑主体控股股东或实际控制人身份证件/证明文件号码
     */
    @TableField("SCID")
    private String scid;

    /**
     * 可疑主体所在银行账号
     */
    @TableField("SCBA")
    private String scba;

    /**
     * 可疑主体所在银行名称
     */
    @TableField("SCBN")
    private String scbn;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

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

    /**
     * 删除标记
     */
    @TableField("DEL_FLAG")
    private String delFlag;


}
