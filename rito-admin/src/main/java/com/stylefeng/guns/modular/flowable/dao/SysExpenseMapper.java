package com.stylefeng.guns.modular.flowable.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.flowable.model.SysExpense;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 测试工作流 Mapper 接口
 * </p>
 *
 * @author wj123
 * @since 2019-06-12
 */
public interface SysExpenseMapper extends BaseMapper<SysExpense> {

    /**
     * 获取列表
     */
    List<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition);
}
