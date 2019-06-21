package com.stylefeng.guns.modular.financial.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.financial.model.FinanceBalanceSheet07Fb;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 财务报表-资产负债07流水表 服务类
 * </p>
 *
 * @author wj123
 * @since 2019-06-17
 */
public interface IFinanceBalanceSheet07FbService extends IService<FinanceBalanceSheet07Fb> {

    /**
     *功能描述:  查询列表
     *
     * @param page
     * @param condition
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author wj
     * @date 2019/6/12 11:19
     */
    List<Map<String, Object>> list(Page page, String condition);
}
