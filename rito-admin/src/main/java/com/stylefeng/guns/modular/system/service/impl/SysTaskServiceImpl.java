package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.state.DelFlag;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.exception.GunsExceptionEnum;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.task.JobService;
import com.stylefeng.guns.core.task.TaskService;
import com.stylefeng.guns.core.task.job.QuartzJob;
import com.stylefeng.guns.modular.system.dao.SysTaskMapper;
import com.stylefeng.guns.modular.system.model.SysTask;
import com.stylefeng.guns.modular.system.service.ISysTaskService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wj123
 * @since 2019-06-13
 */
@Service
public class SysTaskServiceImpl extends ServiceImpl<SysTaskMapper, SysTask> implements ISysTaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Resource
    private SysTaskMapper sysTaskMapper;

    @Autowired
    private JobService jobService;


    @Override
    public List<Map<String, Object>> list(Page page, String condition) {
        List<Map<String, Object>> list = this.baseMapper.list(page, condition);
        return list;
    }

    @Override
    public boolean insert(SysTask task) {
        logger.info("新增定时任务%s", task.getName());
        Integer insert = sysTaskMapper.insert(task);
        try {
            jobService.addJob(jobService.getJob(task));
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
        return true;
    }

    /**
     * 功能描述: 更新并且重置任务执行时间
     *
     * @param task
     * @return boolean
     * @author wj
     * @date 2019/6/14 10:46
     */
    @Override
    public boolean updateById(SysTask task) {
        logger.info("更新定时任务{}", task.getName());
        sysTaskMapper.updateById(task);
        try {
            QuartzJob job = jobService.getJob(task.getId().toString(), task.getJobGroup());
            if (job != null) {
                jobService.deleteJob(job);
            }
            jobService.addJob(jobService.getJob(task));
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }

        return true;
    }

    public SysTask disable(Long id) {
        SysTask task = this.sysTaskMapper.selectById(id);
        task.setDisabled(true);
        sysTaskMapper.updateById(task);
        logger.info("禁用定时任务{}", id.toString());
        try {
            QuartzJob job = jobService.getJob(task.getId().toString(), task.getJobGroup());
            if (job != null) {
                jobService.deleteJob(job);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
        return task;
    }


    public SysTask enable(Long id) {
        SysTask task = this.sysTaskMapper.selectById(id);
        task.setDisabled(false);
        sysTaskMapper.updateById(task);
        logger.info("启用定时任务{}", id.toString());
        try {
            QuartzJob job = jobService.getJob(task.getId().toString(), task.getJobGroup());
            if (job != null) {
                jobService.deleteJob(job);
            }
            if (!task.isDisabled()) {
                jobService.addJob(jobService.getJob(task));
            }
        } catch (SchedulerException e) {
            throw new GunsException(GunsExceptionEnum.TASK_CONFIG_ERROR);
        }
        return task;
    }


    public void delete(Long id) {
        SysTask task = this.sysTaskMapper.selectById(id);
        task.setDisabled(true);
        task.setDeleteFlag(DelFlag.DELETED.getCode());
        task.setCreateUser(ShiroKit.getUserNotNull().getId());
        task.setUpdateTime(new Date());
        sysTaskMapper.updateById(task);
        logger.info("删除定时任务{}", id.toString());
        try {
            QuartzJob job = jobService.getJob(task.getId().toString(), task.getJobGroup());
            if (job != null) {
                jobService.deleteJob(job);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
