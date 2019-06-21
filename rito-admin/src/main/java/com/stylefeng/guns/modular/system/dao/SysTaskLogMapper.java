package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.SysTaskLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务日志 Mapper 接口
 * </p>
 *
 * @author wj123
 * @since 2019-06-13
 */
public interface SysTaskLogMapper extends BaseMapper<SysTaskLog> {

    /**
     * 获取列表
     */
    List<Map<String, Object>> list(@Param("page") Page page, @Param("taskId") String taskId);
}
