package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.SysTask;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wj123
 * @since 2019-06-13
 */
public interface ISysTaskService extends IService<SysTask> {

    List<Map<String, Object>> list(Page page, String condition);

    /**
     *功能描述:  禁用定时任务
     *
     * @param id
    * @return com.stylefeng.guns.modular.system.model.SysTask
    * @author wj
    * @date 2019/6/13 15:13
    */
    SysTask disable(Long id);

    /**
     *功能描述: 启用定时任务
     *
     * @param id
    * @return com.stylefeng.guns.modular.system.model.SysTask
    * @author wj
    * @date 2019/6/13 15:13
    */
    SysTask enable(Long id);

    void delete(Long sysTaskId);
}
