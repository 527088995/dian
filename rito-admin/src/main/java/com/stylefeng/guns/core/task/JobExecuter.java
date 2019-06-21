package com.stylefeng.guns.core.task;

import com.stylefeng.guns.core.task.job.QuartzJob;
import com.stylefeng.guns.modular.system.dao.SysTaskLogMapper;
import com.stylefeng.guns.modular.system.dao.SysTaskMapper;
import com.stylefeng.guns.modular.system.model.SysTask;
import com.stylefeng.guns.modular.system.model.SysTaskLog;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Component
public abstract class JobExecuter {

    protected static final Logger log = LoggerFactory.getLogger(JobExecuter.class);

    @Resource
    private SysTaskMapper sysTaskMapper;

    @Resource
    private SysTaskLogMapper sysTaskLogMapper;

    private QuartzJob job;

    public void setJob(QuartzJob job) {
        this.job = job;
    }

    public void execute() {
        Map dataMap = job.getDataMap();
        String taskId = job.getJobName();
        SysTask task = sysTaskMapper.selectById(Long.valueOf(taskId));
        final String taskName = task.getName();
        log.info(">>>>>>>>>>>>>>>>>开始执行定时任务[" + taskName + "]...<<<<<<<<<<<<<<<<<<<");

        String exeResult = "执行成功";
        final SysTaskLog taskLog = new SysTaskLog();
        taskLog.setName(taskName);
        final Date exeAt = new Date();
        taskLog.setExecAt(exeAt);
        taskLog.setCreateTime(new Date());
        taskLog.setIdTask(task.getId());
        //默认是成功 出异常后改成失败
        taskLog.setExecSuccess(SysTaskLog.EXE_SUCCESS_RESULT);
        try {
            execute(dataMap);
        } catch (Exception e) {
            log.error("exeucte " + getClass().getName() + " error : ", e);
            exeResult = "执行失败\n";
            exeResult += ExceptionUtils.getStackTrace(e);
            taskLog.setExecSuccess(SysTaskLog.EXE_FAILURE_RESULT);
            taskLog.setJobException(e.getClass().getName());
        }
        task.setExecResult(exeResult);
        task.setExecAt(exeAt);
        sysTaskLogMapper.insert(taskLog);
        sysTaskMapper.updateById(task);
        log.info(">>>>>>>>>>>>>>>>>执行定时任务[" + taskName + "]结束<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * @param dataMap 数据库配置的参数
     */
    public abstract void execute(Map<String, Object> dataMap) throws Exception;

    public String getEmail() {
        return getEmail("527088995@qq.com");
    }

    public String getEmail(String defaultEmail) {
        Map<String, Object> dataMap = job.getDataMap();
        String toEmail = null;
        if (dataMap != null && dataMap.containsKey("email")) {
            toEmail = StringUtils.stripToNull(String.valueOf(dataMap.get("email")));
        }
        if (StringUtils.isEmpty(toEmail)) {
            toEmail = defaultEmail;
        }
        return toEmail;
    }

}
