package com.stylefeng.guns.modular.guar.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 企业担保-抵质押合同流水表
 * </p>
 *
 * @author wj123
 * @since 2019-06-19
 */
@TableName("GUAR_MOTGA_PLEDGE_FB")
@Data
public class GuarMotgaPledgeFb implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业担保账户信息ID
     */
    @TableField("GUAR_ACCT_ID")
    private Long guarAcctId;
    /**
     * 合同标识码
     */
    @TableField("CCC")
    private String ccc;
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
