package com.stylefeng.guns.modular.financial.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.financial.model.FinanceIncomeStatement07Fb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 利润及利润分配07流水表 Mapper 接口
 * </p>
 *
 * @author wj123
 * @since 2019-06-17
 */
public interface FinanceIncomeStatement07FbMapper extends BaseMapper<FinanceIncomeStatement07Fb> {

    /**
     * 获取列表
     */
    List<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition);
}
