package com.stylefeng.guns.modular.flowable.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.flowable.model.SysExpense;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Expense;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 测试工作流 服务类
 * </p>
 *
 * @author wj123
 * @since 2019-06-12
 */
public interface ISysExpenseService extends IService<SysExpense> {

    /**
     * 新增一个报销单
     */
    void add(SysExpense expense);
    /**
     *功能描述:  查询列表
     *
     * @param page
     * @param condition
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author wj
     * @date 2019/6/12 11:19
     */
    List<Map<String, Object>> list(Page page, String condition);

    /**
     * 绘画当前流程图
     */
    void printProcessImage(Long expenseId) throws IOException;
}
