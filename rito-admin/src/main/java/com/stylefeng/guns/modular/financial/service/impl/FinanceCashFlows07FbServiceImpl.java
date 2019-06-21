package com.stylefeng.guns.modular.financial.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.financial.model.FinanceCashFlows07Fb;
import com.stylefeng.guns.modular.financial.dao.FinanceCashFlows07FbMapper;
import com.stylefeng.guns.modular.financial.service.IFinanceCashFlows07FbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 财务报表-现金流量表信息07流水表 服务实现类
 * </p>
 *
 * @author wj123
 * @since 2019-06-17
 */
@Service
public class FinanceCashFlows07FbServiceImpl extends ServiceImpl<FinanceCashFlows07FbMapper, FinanceCashFlows07Fb> implements IFinanceCashFlows07FbService {

    @Override
    public List<Map<String, Object>> list(Page page, String condition) {
        List<Map<String, Object>> list = this.baseMapper.list(page, condition);
        return list;
    }
}
