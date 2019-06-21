package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.SysTaskLog;
import com.stylefeng.guns.modular.system.dao.SysTaskLogMapper;
import com.stylefeng.guns.modular.system.service.ISysTaskLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务日志 服务实现类
 * </p>
 *
 * @author wj123
 * @since 2019-06-13
 */
@Service
public class SysTaskLogServiceImpl extends ServiceImpl<SysTaskLogMapper, SysTaskLog> implements ISysTaskLogService {

    @Override
    public List<Map<String, Object>> list(Page page, String condition) {
        List<Map<String, Object>> list = this.baseMapper.list(page, condition);
        return list;
    }
}
