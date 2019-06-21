package com.stylefeng.guns.modular.financial.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.financial.model.FinanceBalanceSheet07Fb;
import com.stylefeng.guns.modular.financial.dao.FinanceBalanceSheet07FbMapper;
import com.stylefeng.guns.modular.financial.service.IFinanceBalanceSheet07FbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 财务报表-资产负债07流水表 服务实现类
 * </p>
 *
 * @author wj123
 * @since 2019-06-17
 */
@Service
public class FinanceBalanceSheet07FbServiceImpl extends ServiceImpl<FinanceBalanceSheet07FbMapper, FinanceBalanceSheet07Fb> implements IFinanceBalanceSheet07FbService {

    @Override
    public List<Map<String, Object>> list(Page page, String condition) {
        List<Map<String, Object>> list = this.baseMapper.list(page, condition);
        return list;
    }
}
