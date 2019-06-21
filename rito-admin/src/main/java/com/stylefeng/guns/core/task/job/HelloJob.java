package com.stylefeng.guns.core.task.job;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.task.JobExecuter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * HelloJob
 *
 * @author zt
 * @version 2018/12/30 0030
 */
@Component
public class HelloJob extends JobExecuter {
    @Override
    public void execute(Map<String, Object> dataMap) throws Exception {
        System.out.println("hello :" + JSON.toJSONString(dataMap));
    }
}
