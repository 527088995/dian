package com.stylefeng.guns.modular.toLoan.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.toLoan.model.EntAcctRltRepymtFb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业借贷-相关还款人信息流水表 Mapper 接口
 * </p>
 *
 * @author wj
 * @since 2019-06-11
 */
public interface EntAcctRltRepymtFbMapper extends BaseMapper<EntAcctRltRepymtFb> {

    /**
     * 获取列表
     */
    List<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition);
}
