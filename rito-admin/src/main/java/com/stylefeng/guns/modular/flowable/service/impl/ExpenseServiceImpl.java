package com.stylefeng.guns.modular.flowable.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.state.ExpenseState;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.modular.flowable.model.SysExpense;
import com.stylefeng.guns.modular.flowable.model.TaskVo;
import com.stylefeng.guns.modular.flowable.service.IExpenseService;
import com.stylefeng.guns.modular.flowable.service.ISysExpenseService;
import com.stylefeng.guns.modular.system.dao.ExpenseMapper;
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

/**
 * <p>
 * 报销表 服务实现类
 * </p>
 *
 * @author ...
 * @since 2017-12-04
 */
@Service
public class ExpenseServiceImpl extends ServiceImpl<ExpenseMapper, Expense> implements IExpenseService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private ISysExpenseService sysExpenseService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Expense expense) {
        //保存业务数据
        expense.setUserid(ShiroKit.getUser().getId());
        expense.setState(ExpenseState.SUBMITING.getCode());

        //启动流程
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskUser", ShiroKit.getUser().getName());
        map.put("money", expense.getMoney());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Expense", map);
        expense.setProcessId(processInstance.getId());
        this.insert(expense);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer expenseId) {
        Expense expense = this.selectById(expenseId);
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processInstanceId(expense.getProcessId()).list();
        for (ProcessInstance processInstance : list) {
            if (processInstance.getId().equals(expense.getProcessId())) {
                runtimeService.deleteProcessInstance(processInstance.getProcessInstanceId(), "取消报销");
            }
        }
        this.deleteById(expenseId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pass(String taskId) {
        //使用任务ID，查询任务对象，获取流程流程实例ID
        Task task = taskService.createTaskQuery()//
                .processInstanceId(taskId)
                .singleResult();

        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "1");
        taskService.complete(task.getId(), map);

        //判断流程是否结束,结束之后修改状态
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                .processInstanceId(task.getProcessInstanceId())//使用流程实例ID查询
                .singleResult();
        Wrapper<SysExpense> wrapper = new EntityWrapper<SysExpense>().eq("PROCESS_ID", task.getProcessInstanceId());
        SysExpense expense = sysExpenseService.selectOne(wrapper);

        //审核通过修改为通过状态
        if (pi == null) {
            expense.setState(ExpenseState.PASS.getCode());
            sysExpenseService.updateById(expense);
        } else {
            //审核通过如果还有记录则为经理或boss审核中
            expense.setState(ExpenseState.CHECKING.getCode());
            sysExpenseService.updateById(expense);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unPass(String taskId) {
        //使用任务ID，查询任务对象，获取流程流程实例ID
        Task task = taskService.createTaskQuery()//
                .processInstanceId(taskId)
                .singleResult();
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "0");
        taskService.complete(task.getId(), map);
        //修改审核状态为未通过
        Wrapper<SysExpense> wrapper = new EntityWrapper<SysExpense>().eq("PROCESS_ID", task.getProcessInstanceId());
        SysExpense expense = sysExpenseService.selectOne(wrapper);
        expense.setState(ExpenseState.UN_PASS.getCode());
        sysExpenseService.updateById(expense);
    }

    @Override
    public List<TaskVo> getProcessList() {
        String name = ShiroKit.getUser().getName();
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(name)
                .orderByTaskCreateTime().desc()
                .list();
        ArrayList<TaskVo> taskVos = new ArrayList<>();
        for (Task task : list) {
            Object money = runtimeService.getVariable(task.getExecutionId(), "money");
            String taskUser = (String) taskService.getVariable(task.getId(), "taskUser");
            boolean flag = false;
            if (name.equals(taskUser)) {
                flag = false;
            } else {
                flag = true;
            }
            taskVos.add(new TaskVo(task.getId(), task.getName(), task.getCreateTime(), taskUser, money, flag));
        }
        return taskVos;
    }

    @Override
    public void printProcessImage(Integer expenseId) throws IOException {
        Expense expense = this.selectById(expenseId);
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

