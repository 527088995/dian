package com.stylefeng.guns.modular.flowable.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.common.constant.state.ExpenseState;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.modular.flowable.model.SysExpense;
import com.stylefeng.guns.modular.flowable.dao.SysExpenseMapper;
import com.stylefeng.guns.modular.flowable.service.ISysExpenseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.model.Expense;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 测试工作流 服务实现类
 * </p>
 *
 * @author wj123
 * @since 2019-06-12
 */
@Service
public class SysExpenseServiceImpl extends ServiceImpl<SysExpenseMapper, SysExpense> implements ISysExpenseService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysExpense expense) {
        //保存业务数据
        expense.setCreateUser(ShiroKit.getUser().getId());
        expense.setState(ExpenseState.SUBMITING.getCode());

        //启动流程
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskUser", ShiroKit.getUser().getName());
        map.put("money", expense.getMoney());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Test", map);
        expense.setProcessId(processInstance.getId());
        this.insert(expense);
    }

    @Override
    public List<Map<String, Object>> list(Page page, String condition) {
        List<Map<String, Object>> list = this.baseMapper.list(page, condition);
        return list;
    }

    @Override
    public void printProcessImage(Long expenseId) throws IOException {
        SysExpense expense = this.baseMapper.selectById(expenseId);
        String processId = expense.getProcessId();
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();

        //流程走完的不显示图
        if (pi == null) {
            return;
        }

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(InstanceId)
                .list();

        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = HttpKit.getResponse().getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
