package com.stylefeng.guns.modular.financial.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.financial.model.FinanceIncomeStatement07Fb;
import com.stylefeng.guns.modular.financial.dao.FinanceIncomeStatement07FbMapper;
import com.stylefeng.guns.modular.financial.service.IFinanceIncomeStatement07FbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 利润及利润分配07流水表 服务实现类
 * </p>
 *
 * @author wj123
 * @since 2019-06-17
 */
@Service
public class FinanceIncomeStatement07FbServiceImpl extends ServiceImpl<FinanceIncomeStatement07FbMapper, FinanceIncomeStatement07Fb> implements IFinanceIncomeStatement07FbService {

    @Override
    public List<Map<String, Object>> list(Page page, String condition) {
        List<Map<String, Object>> list = this.baseMapper.list(page, condition);
        return list;
    }
}
