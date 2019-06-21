package com.stylefeng.guns.modular.guar.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.guar.model.GuarResponsibleInfoFb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业担保-相关还款责任人流水表 Mapper 接口
 * </p>
 *
 * @author wj123
 * @since 2019-06-19
 */
public interface GuarResponsibleInfoFbMapper extends BaseMapper<GuarResponsibleInfoFb> {

    /**
     * 获取列表
     */
    List<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition);
}
