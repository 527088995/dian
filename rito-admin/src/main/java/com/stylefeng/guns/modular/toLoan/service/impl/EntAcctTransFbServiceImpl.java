package com.stylefeng.guns.modular.toLoan.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.toLoan.model.EntAcctTransFb;
import com.stylefeng.guns.modular.toLoan.dao.EntAcctTransFbMapper;
import com.stylefeng.guns.modular.toLoan.service.IEntAcctTransFbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业借贷-特定交易信息流水表 服务实现类
 * </p>
 *
 * @author wj123
 * @since 2019-06-12
 */
@Service
public class EntAcctTransFbServiceImpl extends ServiceImpl<EntAcctTransFbMapper, EntAcctTransFb> implements IEntAcctTransFbService {

    @Override
    public List<Map<String, Object>> list(Page page, String condition) {
        List<Map<String, Object>> list = this.baseMapper.list(page, condition);
        return list;
    }
}
