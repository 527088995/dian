package com.stylefeng.guns.modular.toLoan.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.toLoan.model.EntAcctCccFb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业借贷-抵质押物信息流水表 Mapper 接口
 * </p>
 *
 * @author wj123
 * @since 2019-06-12
 */
public interface EntAcctCccFbMapper extends BaseMapper<EntAcctCccFb> {

    /**
     * 获取列表
     */
    List<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition);}
