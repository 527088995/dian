package com.stylefeng.guns.core.listener;

import com.stylefeng.guns.core.task.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description:task监听器负责初始化task
 * @Auther: wj
 * @Date: 2019/6/14 10:34
 */
@Component
@Order(value = 1)
public class ScheduleJobInitListener implements CommandLineRunner {

    @Autowired
    JobService scheduleJobService;

    @Override
    public void run(String... arg0) throws Exception {
        try {
            scheduleJobService.initSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
