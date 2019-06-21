package com.stylefeng.guns.modular.toLoan.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.toLoan.dao.EntAcctRltRepymtFbMapper;
import com.stylefeng.guns.modular.toLoan.model.EntAcctRltRepymtFb;
import com.stylefeng.guns.modular.toLoan.service.IEntAcctRltRepymtFbService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业借贷-相关还款人信息流水表 服务实现类
 * </p>
 *
 * @author wj
 * @since 2019-06-11
 */
@Service
public class EntAcctRltRepymtFbServiceImpl extends ServiceImpl<EntAcctRltRepymtFbMapper, EntAcctRltRepymtFb> implements IEntAcctRltRepymtFbService {


    @Override
    public List<Map<String, Object>> list(Page page, String condition) {
        List<Map<String, Object>> list = this.baseMapper.list(page, condition);
        return list;
    }
}
