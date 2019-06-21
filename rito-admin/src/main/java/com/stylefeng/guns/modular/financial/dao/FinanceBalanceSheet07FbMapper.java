package com.stylefeng.guns.modular.financial.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.financial.model.FinanceBalanceSheet07Fb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 财务报表-资产负债07流水表 Mapper 接口
 * </p>
 *
 * @author wj123
 * @since 2019-06-17
 */
public interface FinanceBalanceSheet07FbMapper extends BaseMapper<FinanceBalanceSheet07Fb> {

    /**
     * 获取列表
     */
    List<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition);
}
