package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.SysTaskLog;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务日志 服务类
 * </p>
 *
 * @author wj123
 * @since 2019-06-13
 */
public interface ISysTaskLogService extends IService<SysTaskLog> {

    List<Map<String, Object>> list(Page page, String condition);
}
