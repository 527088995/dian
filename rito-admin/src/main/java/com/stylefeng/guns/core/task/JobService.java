package com.stylefeng.guns.core.task;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.exception.GunsExceptionEnum;
import com.stylefeng.guns.core.task.job.QuartzJob;
import com.stylefeng.guns.modular.system.model.SysTask;
import com.stylefeng.guns.modular.system.service.ISysTaskService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 任务服务
 */
@Service
public class JobService {
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ISysTaskService taskService;

    /**
     * 获取单个任务
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */

    public QuartzJob getJob(String jobName, String jobGroup) throws SchedulerException {
        QuartzJob job = null;
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if (null != trigger) {
            job = new QuartzJob();
            job.setJobName(jobName);
            job.setJobGroup(jobGroup);
            job.setDescription("触发器:" + trigger.getKey());
            job.setNextTime(trigger.getNextFireTime());
            job.setPreviousTime(trigger.getPreviousFireTime());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
        }
        return job;
    }


    public QuartzJob getJob(SysTask task) {
        QuartzJob job = new QuartzJob();
        if (task != null) {
            job = new QuartzJob();
            job.setJobName(String.valueOf(task.getId()));
            job.setJobGroup(task.getJobGroup());
            job.setCronExpression(task.getCron());
            job.setConcurrent(task.isConcurrent());
            job.setJobClass(task.getJobClass());
            job.setDescription(task.getName());
            if("1".equals(task.getDisabled())){
                job.setDisabled(true);
            }else {
                job.setDisabled(false);
            }
            if (StringUtils.isNotBlank(task.getData())) {
                try {
                    Map<String, Object> dataMap = JSON.parseObject(task.getData(), Map.class);
                    job.setDataMap(dataMap);
                } catch (Exception e) {
                    e.printStackTrace();
                     throw  new GunsException(GunsExceptionEnum.TASK_CONFIG_ERROR);
                }
            }
        }
        return job;
    }

    /**
     * 添加任务
     *
     * @param job
     * @throws SchedulerException
     */

    public boolean addJob(QuartzJob job) throws SchedulerException {
        logger.info("新增任务Id：{}, name:{}", job.getJobName(), job.getDescription());
        if (job == null || job.isDisabled()) {
            return false;
        }
        if (!CronExpression.isValidExpression(job.getCronExpression())) {
            logger.error("时间表达式错误（" + job.getJobName() + "," + job.getJobGroup() + "）," + job.getCronExpression());
            return false;
        } else {
            // 任务名称和任务组设置规则：    // 名称：task_1 ..    // 组 ：group_1 ..
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 不存在，创建一个
            if (null == trigger) {
                //是否允许并发执行
                Class<? extends Job> clazz = job.isConcurrent() ? BaseJob.class : NoConurrentBaseJob.class;
                JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("job", job);
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                // 按新的表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {     // trigger已存在，则更新相应的定时设置
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
        return true;
    }

    /**
     * 删除任务
     */

    public boolean deleteJob(QuartzJob job) {
        logger.info("`删除任务：{}", job.getJobName());
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        try {
            scheduler.deleteJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        Wrapper<SysTask> wrapper=new EntityWrapper<>();
        wrapper.eq("DELETE_FLAG",0);
        List<SysTask> jobList = taskService.selectList(wrapper);
        for (SysTask task : jobList) {
            if ("0".equals(task.getDisabled())) {
                taskService.updateById(task);
            }
        }
    }
}
