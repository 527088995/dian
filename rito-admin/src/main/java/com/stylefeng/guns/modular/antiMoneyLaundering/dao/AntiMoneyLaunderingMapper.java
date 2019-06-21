package com.stylefeng.guns.modular.antiMoneyLaundering.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.antiMoneyLaundering.model.AntiMoneyLaundering;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 反洗钱 Mapper 接口
 * </p>
 *
 * @author wj
 * @since 2019-04-19
 */
public interface AntiMoneyLaunderingMapper extends BaseMapper<AntiMoneyLaundering> {

    /**
     * 获取通知列表
     */
    List<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition);

    /**
     * 自动生成XML
     */
    AntiMoneyLaundering createXML(@Param("antiId") Long antiId);
}

